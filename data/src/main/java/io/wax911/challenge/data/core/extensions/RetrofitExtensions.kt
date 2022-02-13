package io.wax911.challenge.data.core.extensions

import io.wax911.challenge.data.core.error.RequestError
import kotlinx.coroutines.delay
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException


/**
 * @return [Response.body] of the response
 *
 * @throws HttpException When the request was not successful
 */
@Throws(HttpException::class)
private fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return requireNotNull(body()) {
        "Response<T>.bodyOrThrow() -> response body was null"
    }
}

/**
 * @return [Boolean] whether or not the request should be retried based on the [exception] received
 */
private fun defaultShouldRetry(exception: Throwable) = when (exception) {
    is HttpException -> exception.code() == 429
    is SocketTimeoutException,
    is IOException -> true
    else -> false
}

private fun Throwable.getNextDelay(
    attempt: Int,
    maxAttempts: Int,
    defaultDelay: Long,
    shouldRetry: (Throwable) -> Boolean
): Long {
    var nextDelay: Long = attempt * attempt * defaultDelay
    Timber.w("Request threw an exception -> $this")

    // The response failed, so lets see if we should retry again
    if (!shouldRetry(this)) {
        Timber.w(this, "Specific request is not allowed to retry on this exception")
        throw this
    }

    if (attempt == maxAttempts) {
        Timber.w(this, "Cannot retry on exception or maximum retries reached")
    }

    if (this is HttpException) {
        // If we have a HttpException, check whether we have a Retry-After
        // header to decide how long to delay
        val retryAfterHeader = response()?.headers()?.get("Retry-After")
        if (retryAfterHeader != null && retryAfterHeader.isNotEmpty()) {
            // Got a Retry-After value, try and parse it to an long
            Timber.i("Rate limit reached")
            try {
                nextDelay = (retryAfterHeader.toLong() + 10).coerceAtLeast(defaultDelay)
            } catch (nfe: NumberFormatException) {
                Timber.e(
                    nfe, "Highly unlikely exception was caught on header retry after"
                )
            }
        }
    }

    Timber.i(
        "Retrying request in $nextDelay ms -> attempt: ${attempt + 1} maxAttempts: $maxAttempts"
    )
    return nextDelay
}

/**
 * Executes the request
 *
 * @param shouldRetry Conditions to determine when a request should be retried
 * @param defaultDelay Initial delay before retrying
 * @param maxAttempts Max number of attempts to retry
 */
internal suspend fun <T> Response<T>.execute(
    defaultDelay: Long,
    maxAttempts: Int,
    shouldRetry: (Throwable) -> Boolean
): T {
    var lastKnownException: Throwable? = null

    repeat(maxAttempts) { attempt ->
        runCatching {
            bodyOrThrow()
        }.onSuccess { response ->
            return response
        }.onFailure { exception ->
            if (lastKnownException != exception)
                lastKnownException = exception
            delay(
                exception.getNextDelay(
                    attempt,
                    maxAttempts,
                    defaultDelay,
                    shouldRetry
                )
            )
        }
    }

    throw lastKnownException ?: RequestError.GeneralError(
        "Unable to recover from unknown error"
    )
}

/**
 * Automatically runs the suspendable operation and returns the body
 *
 * @param shouldRetry Conditions to determine when a request should be retried
 * @param firstDelay Initial delay before retrying
 * @param maxAttempts Max number of attempts to retry
 *
 * @throws HttpException When the [maxAttempts] have been exhausted, or unhandled exception
 */
@Throws(HttpException::class)
internal suspend fun <T> Response<T>.fetch(
    firstDelay: Long = 500,
    maxAttempts: Int = 3,
    shouldRetry: (Throwable) -> Boolean = ::defaultShouldRetry,
) = execute(
    firstDelay,
    maxAttempts,
    shouldRetry
)
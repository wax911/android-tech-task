package io.wax911.challenge.data.core.error

sealed class RequestError(message: String) : Throwable(message) {
    data class GeneralError(
        override val message: String
    ) : RequestError(message)
}

package io.wax911.challenge.navigation.extensions

import android.content.Context
import android.content.Intent
import timber.log.Timber
import io.wax911.challenge.navigation.router.NavigationRouter

/**
 * Builds an activity intent from the navigation component
 */
fun NavigationRouter.forIntent(
    context: Context,
    flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK,
    action: String = Intent.ACTION_VIEW,
): Intent? {
    val intent = provider.activity(context)
    intent?.flags = flags
    intent?.action = action
    return intent
}

/**
 * Builds an activity intent and starts it
 */
fun NavigationRouter.startActivity(
    context: Context?,
    flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK,
    action: String = Intent.ACTION_VIEW,
) {
    runCatching {
        val intent = forIntent(
            requireNotNull(context),
            flags,
            action
        )
        context.startActivity(intent)
    }.onFailure {
        Timber.e(it)
    }
}
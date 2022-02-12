package io.wax911.challenge.navigation.provider

import android.content.Context
import android.content.Intent

/**
 * Provider for navigation components
 */
interface INavigationProvider {
    fun activity(context: Context): Intent? = null
}
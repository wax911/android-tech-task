package io.wax911.challenge.navigation.router

import org.koin.core.component.KoinComponent
import io.wax911.challenge.navigation.provider.INavigationProvider

/**
 * Router for navigation components
 */
abstract class NavigationRouter : KoinComponent {

    /**
     * Feature provider contract
     */
    internal abstract val provider: INavigationProvider
}
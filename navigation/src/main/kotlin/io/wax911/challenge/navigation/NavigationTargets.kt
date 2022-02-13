package io.wax911.challenge.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import org.koin.core.component.inject
import io.wax911.challenge.navigation.provider.INavigationProvider
import io.wax911.challenge.navigation.router.NavigationRouter

object MainRouter : NavigationRouter() {
    /**
     * Feature provider contract
     */
    override val provider by inject<Provider>()

    interface Provider : INavigationProvider {
        fun fragment(): Class<out Fragment>
    }

    fun forFragment() = provider.fragment()
}

object DetailRouter : NavigationRouter() {
    /**
     * Feature provider contract
     */
    override val provider by inject<Provider>()

    interface Provider : INavigationProvider {
        fun fragment(): Class<out Fragment>
    }

    fun forFragment() = provider.fragment()
}

object LandingRouter : NavigationRouter() {
    /**
     * Feature provider contract
     */
    override val provider by inject<Provider>()

    interface Provider : INavigationProvider {
        fun fragment(): Class<out Fragment>
    }

    fun forFragment() = provider.fragment()
}
package io.wax911.challenge.initializer

import android.content.Context
import androidx.startup.Initializer
import io.wax911.challenge.BuildConfig
import io.wax911.challenge.core.initializer.contract.AbstractCoreInitializer
import io.wax911.challenge.core.initializer.injector.InjectorInitializer
import timber.log.Timber

class ApplicationInitializer : AbstractCoreInitializer<Unit>() {

    /**
     * Initializes and a component given the application [Context]
     *
     * @param context The application context.
     */
    override fun create(context: Context) {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    /**
     * @return A list of dependencies that this [Initializer] depends on. This is
     * used to determine initialization order of [Initializer]s.
     *
     * For e.g. if a [Initializer] `B` defines another
     * [Initializer] `A` as its dependency, then `A` gets initialized before `B`.
     */
    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf(InjectorInitializer::class.java)
}
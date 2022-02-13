package io.wax911.challenge.core.initializer.injector

import android.content.Context
import io.wax911.challenge.core.initializer.contract.AbstractCoreInitializer
import io.wax911.challenge.core.koin.coreModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class InjectorInitializer : AbstractCoreInitializer<Unit>() {

    /**
     * Initializes and a component given the application [Context]
     *
     * @param context The application context.
     */
    override fun create(context: Context) {
        startKoin {
            androidContext(context)
            fragmentFactory()
            modules(coreModules)
        }
    }
}
package io.wax911.challenge.feature.landing.initializer

import android.content.Context
import io.wax911.challenge.core.initializer.contract.AbstractFeatureInitializer
import io.wax911.challenge.core.koin.helper.FeatureModuleHelper.Companion.loadModules
import io.wax911.challenge.feature.landing.koin.featureModules

class FeatureInitializer : AbstractFeatureInitializer<Unit>() {
    /**
     * Initializes and a component given the application [Context]
     *
     * @param context The application context.
     */
    override fun create(context: Context) {
        featureModules.loadModules()
    }
}

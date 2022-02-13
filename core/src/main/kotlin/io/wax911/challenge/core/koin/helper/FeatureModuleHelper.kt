package io.wax911.challenge.core.koin.helper

import androidx.lifecycle.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import timber.log.Timber

/**
 * Module loader helper for dynamic features which is lifecycle aware
 *
 * @param modules A list of modules which need to be loaded
 * @param unloadOnDestroy If the registered module need to be unloaded on lifecycle destroy
 */
class FeatureModuleHelper(
    private val modules: List<Module>,
    private val unloadOnDestroy: Boolean = false
) : DefaultLifecycleObserver {

    /**
     * Notifies that `ON_CREATE` event occurred.
     *
     *
     * This method will be called after the [LifecycleOwner]'s `onCreate`
     * method returns.
     *
     * @param owner the component, whose state was changed
     */
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Timber.v("Loading ${modules.size} dynamic feature modules into $owner")
        loadModules()
    }

    /**
     * Notifies that `ON_DESTROY` event occurred.
     *
     *
     * This method will be called before the [LifecycleOwner]'s `onDestroy` method
     * is called.
     *
     * @param owner the component, whose state was changed
     */
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        if (unloadOnDestroy) {
            Timber.v("Unloading ${modules.size} feature modules from $owner")
            unloadModules()
        }
    }

    companion object {
        /**
         * load Koin modules in global Koin context
         */
        fun FeatureModuleHelper.loadModules() = loadKoinModules(modules)

        /**
         * unload Koin modules from global Koin context
         */
        fun FeatureModuleHelper.unloadModules() = unloadKoinModules(modules)
    }
}
package io.wax911.challenge.buildSrc.plugins.strategy

import org.gradle.api.artifacts.dsl.DependencyHandler
import io.wax911.challenge.buildSrc.module.Modules
import io.wax911.challenge.buildSrc.extension.*
import io.wax911.challenge.buildSrc.Libraries
import org.gradle.api.Project

internal class DependencyStrategy(private val project: Project) {

    private fun DependencyHandler.applyDefaultDependencies() {
        implementation(Libraries.JetBrains.Kotlin.stdlib)
        if (!project.isDomainModule())
            implementation(Libraries.timber)

        test(Libraries.junit)
        test(Libraries.Mockk.mockk)
    }

    private fun DependencyHandler.applyAndroidTestDependencies() {
        androidTest(Libraries.AndroidX.Test.coreKtx)
        androidTest(Libraries.AndroidX.Test.rules)
        androidTest(Libraries.AndroidX.Test.runner)
        androidTest(Libraries.AndroidX.Test.Espresso.core)
        androidTest(Libraries.AndroidX.Test.Extension.junitKtx)
        androidTest(Libraries.Mockk.mockkAndroid)
    }

    private fun DependencyHandler.applyLifeCycleDependencies() {
        implementation(Libraries.AndroidX.Lifecycle.liveDataCoreKtx)
        implementation(Libraries.AndroidX.Lifecycle.runTimeKtx)
        implementation(Libraries.AndroidX.Lifecycle.liveDataKtx)
        implementation(Libraries.AndroidX.Lifecycle.extensions)
    }

    private fun DependencyHandler.applyCoroutinesDependencies() {
        implementation(Libraries.JetBrains.KotlinX.Coroutines.android)
        implementation(Libraries.JetBrains.KotlinX.Coroutines.core)

        test(Libraries.JetBrains.KotlinX.Coroutines.test)
        androidTest(Libraries.CashApp.Turbine.turbine)
    }

    private fun DependencyHandler.applyKoinDependencies() {
        implementation(Libraries.Koin.core)
        androidTest(Libraries.Koin.Test.test)
        androidTest(Libraries.Koin.Test.testJUnit4)
        if (project.hasKoinAndroidSupport()) {
            implementation(Libraries.Koin.android)
            implementation(Libraries.Koin.AndroidX.workManager)
        }
    }

    private fun DependencyHandler.applyOtherDependencies() {
        when (project.name) {
            Modules.App.Main.id -> {

            }
            Modules.App.Core.id -> {

            }
            Modules.App.Data.id -> {

            }
        }
    }

    fun applyDependenciesOn(handler: DependencyHandler) {
        handler.applyDefaultDependencies()
        if (!project.isDomainModule() || !project.isNavigationModule()) {
            handler.applyLifeCycleDependencies()
            handler.applyAndroidTestDependencies()
            handler.applyCoroutinesDependencies()
            handler.applyKoinDependencies()
            handler.applyOtherDependencies()
        }
    }
}
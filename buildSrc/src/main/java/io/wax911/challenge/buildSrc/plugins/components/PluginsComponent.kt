package io.wax911.challenge.buildSrc.plugins.components

import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import io.wax911.challenge.buildSrc.extension.isAppModule
import io.wax911.challenge.buildSrc.extension.baseExtension
import io.wax911.challenge.buildSrc.extension.hasKaptSupport
import io.wax911.challenge.buildSrc.extension.hasKotlinAndroidExtensionSupport

private fun addAndroidPlugin(project: Project, pluginContainer: PluginContainer) {
    if (project.isAppModule()) pluginContainer.apply("com.android.application")
    else pluginContainer.apply("com.android.library")
}

private fun addKotlinAndroidPlugin(pluginContainer: PluginContainer) {
    pluginContainer.apply("kotlin-android")
    pluginContainer.apply("com.diffplug.spotless")
}

private fun addAnnotationProcessor(project: Project, pluginContainer: PluginContainer) {
    if (project.hasKaptSupport())
        pluginContainer.apply("kotlin-kapt")
}

private fun addKotlinAndroidExtensions(project: Project, pluginContainer: PluginContainer) {
    if (project.hasKotlinAndroidExtensionSupport())
        pluginContainer.apply("kotlin-parcelize")
}

internal fun Project.configurePlugins() {
    addAndroidPlugin(project, plugins)
    addKotlinAndroidPlugin(plugins)
    addKotlinAndroidExtensions(project, plugins)
    addAnnotationProcessor(project, plugins)
}

internal fun Project.configureAdditionalPlugins() {
    if (isAppModule()) {
        if (file("google-services.json").exists()) {
            println("google-services.json found, applying services and crashlytics plugins")
            plugins.apply("com.google.gms.google-services")
            plugins.apply("com.google.firebase.crashlytics")
        } else println("google-services.json cannot be found and will not be using any of the google plugins")
    }
}
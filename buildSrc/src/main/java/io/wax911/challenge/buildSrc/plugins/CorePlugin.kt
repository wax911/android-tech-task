package io.wax911.challenge.buildSrc.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import io.wax911.challenge.buildSrc.plugins.components.configureAndroid
import io.wax911.challenge.buildSrc.plugins.components.configureDependencies
import io.wax911.challenge.buildSrc.plugins.components.configureAdditionalPlugins
import io.wax911.challenge.buildSrc.plugins.components.configureOptions
import io.wax911.challenge.buildSrc.plugins.components.configurePlugins

/**
 * Custom gradle plugin to apply common module configurations
 */
class CorePlugin : Plugin<Project> {

    /**
     * Inspecting available extensions
     */
    @Suppress("UnstableApiUsage")
    internal fun Project.availableExtensions() {
        val extensionSchema = project.extensions.extensionsSchema
        extensionSchema.forEach {
            println("Available extension for module ${project.path}: ${it.name} -> ${it.publicType}")
        }
    }

    /**
     * Inspecting available components
     */
    @Suppress("UnstableApiUsage")
    internal fun Project.availableComponents() {
        val collectionSchema = project.components.asMap
        collectionSchema.forEach {
            println("Available component for module ${project.path}: ${it.key} -> ${it.value}")
        }
    }

    /**
     * Apply this plugin to the given target object.
     *
     * @param target The target object
     */
    override fun apply(target: Project) {
        target.configurePlugins()
        target.configureAndroid()
        target.configureOptions()
        target.configureDependencies()
        target.configureAdditionalPlugins()
        target.availableComponents()
        target.availableExtensions()
    }
}
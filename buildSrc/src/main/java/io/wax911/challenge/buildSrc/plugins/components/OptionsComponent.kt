package io.wax911.challenge.buildSrc.plugins.components

import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.BaseExtension
import com.android.build.api.dsl.LibraryBuildType
import com.android.build.api.dsl.LibraryDefaultConfig
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import io.wax911.challenge.buildSrc.common.Configuration
import io.wax911.challenge.buildSrc.extension.isCoreModule
import io.wax911.challenge.buildSrc.extension.isDataModule
import io.wax911.challenge.buildSrc.extension.libraryExtension
import java.util.*

private fun Properties.applyToBuildConfigFor(buildType: LibraryBuildType) {
    forEach { propEntry ->
        val key = propEntry.key as String
        val value = propEntry.value as String
        println("Adding build config field property -> key: $key value: $value")
        buildType.buildConfigField("String", key, value)
    }
}

private fun NamedDomainObjectContainer<LibraryBuildType>.applyVersionProperties() {
    asMap.forEach { buildTypeEntry ->
        println("Adding version build configuration fields -> ${buildTypeEntry.key}")
        val buildType = buildTypeEntry.value

        buildType.buildConfigField("String", "versionName", "\"${Configuration.versionName}\"")
        buildType.buildConfigField("int", "versionCode", Configuration.versionCode.toString())
    }
}

private fun NamedDomainObjectContainer<LibraryBuildType>.applyConfigurationProperties(project: Project) {
    asMap.forEach { buildTypeEntry ->
        println("Configuring build type -> ${buildTypeEntry.key}")
        val buildType = buildTypeEntry.value

        buildType.buildConfigField("String", "versionName", "\"${Configuration.versionName}\"")
        buildType.buildConfigField("int", "versionCode", Configuration.versionCode.toString())

        val secretsFile = project.file(".config/secrets.properties")
        if (secretsFile.exists())
            secretsFile.inputStream().use { fis ->
                Properties().run {
                    load(fis); applyToBuildConfigFor(buildType)
                }
            }

        val configurationFile = project.file(".config/configuration.properties")
        if (configurationFile.exists())
            configurationFile.inputStream().use { fis ->
                Properties().run {
                    load(fis); applyToBuildConfigFor(buildType)
                }
            }
    }
}

private fun LibraryDefaultConfig.applyRoomCompilerOptions(project: Project) {
    println("Adding java compiler options for room on module-> ${project.path}")
    javaCompileOptions {
        annotationProcessorOptions {
            arguments(
                mapOf(
                    "room.schemaLocation" to "${project.projectDir}/schemas",
                    "room.expandingProjections" to "true",
                    "room.incremental" to "true"
                )
            )
        }
    }
}

internal fun Project.configureOptions() {
    if (isDataModule()) {
        libraryExtension().run {
            defaultConfig {
                applyRoomCompilerOptions(this@configureOptions)
            }
            buildTypes {
                applyConfigurationProperties(this@configureOptions)
            }
        }
    }

    if (isCoreModule()) {
        libraryExtension().run {
            buildTypes {
                applyVersionProperties()
            }
        }
    }
}
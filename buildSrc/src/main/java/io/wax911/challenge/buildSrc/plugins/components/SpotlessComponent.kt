package io.wax911.challenge.buildSrc.plugins.components

import io.wax911.challenge.buildSrc.extension.spotlessExtension
import io.wax911.challenge.buildSrc.common.Versions
import org.gradle.api.Project

internal fun Project.configureSpotless(): Unit = spotlessExtension().run {
    kotlin {
        target("**/*.kt")
        targetExclude("$buildDir/**/*.kt", "bin/**/*.kt")
        ktlint(Versions.ktlint).userData(
            mapOf("android" to "true")
        )
        licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
    }
}

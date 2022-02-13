import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(io.wax911.challenge.buildSrc.Libraries.Android.Tools.buildGradle)
        classpath(io.wax911.challenge.buildSrc.Libraries.JetBrains.Kotlin.Gradle.plugin)
        classpath(io.wax911.challenge.buildSrc.Libraries.JetBrains.Kotlin.Serialization.serialization)

        classpath(io.wax911.challenge.buildSrc.Libraries.Koin.Gradle.plugin)

        classpath(io.wax911.challenge.buildSrc.Libraries.Google.Services.googleServices)
        classpath(io.wax911.challenge.buildSrc.Libraries.Google.Firebase.Crashlytics.Gradle.plugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl(io.wax911.challenge.buildSrc.Libraries.Repositories.jitPack)
        }
    }

    configurations.all {
        // TODO: handle conflicts
    }
}

plugins.apply("koin")

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.create(
    "dependencyUpdates",
    DependencyUpdatesTask::class.java
) {
    checkForGradleUpdate = false
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
    resolutionStrategy {
        componentSelection {
            all {
                val reject = listOf("preview", "m")
                    .map { qualifier ->
                        val pattern = "(?i).*[.-]$qualifier[.\\d-]*"
                        Regex(pattern, RegexOption.IGNORE_CASE)
                    }
                    .any { it.matches(candidate.version) }
                if (reject)
                    reject("Preview releases not wanted")
            }
        }
    }
}

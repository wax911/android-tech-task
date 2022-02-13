package io.wax911.challenge.buildSrc.common

object Configuration {

    private const val major = 1
    private const val minor = 0
    private const val patch = 0
    private const val candidate = 0

    private const val channel = "dev"

    const val buildTools = "32.0.0"
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 21

    /**
     * **RR**_X.Y.Z_
     * > **RR** reserved for build flavours and **X.Y.Z** follow the [versionName] convention
     */
    const val versionCode = major.times(1_000_000_000) +
            minor.times(1_000_000) +
            patch.times(1_000) +
            candidate.times(1)

    /**
     * Naming schema: X.Y.Z-variant##
     * > **X**(Major).**Y**(Minor).**Z**(Patch)
     */
    val versionName = if (candidate > 0)
        "$major.$minor.$patch-$channel$candidate"
    else
        "$major.$minor.$patch"
}
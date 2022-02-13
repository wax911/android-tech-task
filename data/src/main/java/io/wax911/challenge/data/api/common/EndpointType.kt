package io.wax911.challenge.data.api.common

import io.wax911.challenge.data.BuildConfig
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

internal enum class EndpointType(val url: HttpUrl) {
    DEFAULT(BuildConfig.apiUrl.toHttpUrl());

    companion object {
        const val DEFAULT_PATH = "endpoint.json"
    }
}
package io.wax911.challenge.core.koin

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import io.wax911.challenge.core.settings.Settings
import io.wax911.challenge.data.core.koin.dataModules
import io.wax911.challenge.data.settings.IClientSettings

private val coreModule = module {
    factory {
        Settings(context = androidContext())
    } bind IClientSettings::class
}

internal val coreModules = listOf(coreModule) + dataModules
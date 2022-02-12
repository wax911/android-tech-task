package io.wax911.challenge.core.koin

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import io.wax911.challenge.core.settings.Settings
import io.wax911.challenge.data.core.koin.dataModules
import io.wax911.challenge.data.settings.IUserSettings

private val coreModule = module {
    factory {
        Settings(context = androidContext())
    } bind IUserSettings::class
}

internal val coreModules = listOf(coreModule) + dataModules
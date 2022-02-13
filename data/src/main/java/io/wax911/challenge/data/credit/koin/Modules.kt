package io.wax911.challenge.data.credit.koin

import io.wax911.challenge.data.core.extensions.api
import io.wax911.challenge.data.core.extensions.store
import io.wax911.challenge.data.credit.source.CreditDataSource
import org.koin.dsl.module

private val sourceModule = module {
    factory {
        CreditDataSource(
            localSource = store().creditDao(),
            remoteSource = api(),
            settings = get()
        )
    }
}

internal val creditModules = listOf(sourceModule)
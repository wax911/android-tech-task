package io.wax911.challenge.data.landing.koin

import io.wax911.challenge.data.landing.repository.LandingRepository
import io.wax911.challenge.data.landing.usecase.LandingUseCase
import io.wax911.challenge.domain.landing.interactor.ILandingUseCase
import io.wax911.challenge.domain.landing.repository.ILandingRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

private val useCaseModule = module {
    factory<ILandingUseCase> {
        LandingUseCase(
            repository = get(),
            dispatcher = Dispatchers.IO
        )
    }
}

private val repositoryModule = module {
    factory<ILandingRepository> {
        LandingRepository(
            dataSource = get()
        )
    }
}

internal val landingModules =
    listOf(
        useCaseModule,
        repositoryModule
    )
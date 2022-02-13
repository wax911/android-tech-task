package io.wax911.challenge.data.detail.koin

import io.wax911.challenge.data.detail.repository.DetailRepository
import io.wax911.challenge.data.detail.usecase.DetailUseCase
import io.wax911.challenge.domain.detail.interactor.IDetailUseCase
import io.wax911.challenge.domain.detail.repository.IDetailRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

private val useCaseModule = module {
    factory<IDetailUseCase> {
        DetailUseCase(
            repository = get(),
            dispatcher = Dispatchers.IO
        )
    }
}

private val repositoryModule = module {
    factory<IDetailRepository> {
        DetailRepository(
            dataSource = get()
        )
    }
}

internal val detailModules =
    listOf(useCaseModule, repositoryModule)
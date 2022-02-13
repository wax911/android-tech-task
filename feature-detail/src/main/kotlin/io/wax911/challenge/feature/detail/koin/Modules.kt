package io.wax911.challenge.feature.detail.koin

import io.wax911.challenge.core.koin.helper.FeatureModuleHelper
import io.wax911.challenge.feature.detail.component.content.DetailContent
import io.wax911.challenge.feature.detail.component.viewmodel.DetailViewModel
import io.wax911.challenge.feature.detail.provider.FeatureProvider
import io.wax911.challenge.navigation.DetailRouter
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val contentModule = module {
    fragment {
        DetailContent()
    }
}

private val viewModelModule = module {
    viewModel {
        DetailViewModel(
            useCase = get()
        )
    }
}

private val providerModule = module {
    factory<DetailRouter.Provider> {
        FeatureProvider()
    }
}

val featureModules = FeatureModuleHelper(
    listOf(contentModule, viewModelModule, providerModule)
)
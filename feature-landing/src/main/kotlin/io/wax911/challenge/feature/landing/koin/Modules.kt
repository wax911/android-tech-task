package io.wax911.challenge.feature.landing.koin

import io.wax911.challenge.core.koin.helper.FeatureModuleHelper
import io.wax911.challenge.feature.landing.component.content.LandingContent
import io.wax911.challenge.feature.landing.component.viewmodel.LandingViewModel
import io.wax911.challenge.feature.landing.provider.FeatureProvider
import io.wax911.challenge.navigation.LandingRouter
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val contentModule = module {
    fragment {
        LandingContent()
    }
}

private val viewModelModule = module {
    viewModel {
        LandingViewModel(
            useCase = get()
        )
    }
}

private val providerModule = module {
    factory<LandingRouter.Provider> {
        FeatureProvider()
    }
}

val featureModules = FeatureModuleHelper(
    listOf(contentModule, viewModelModule, providerModule)
)
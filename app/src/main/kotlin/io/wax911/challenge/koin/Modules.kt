package io.wax911.challenge.koin

import io.wax911.challenge.core.koin.helper.FeatureModuleHelper
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val contentModule =  module {

}

private val viewModelModule = module {

}

internal val appModules = FeatureModuleHelper(
    listOf(contentModule, viewModelModule)
)
package io.wax911.challenge.feature.landing.component.viewmodel

import androidx.lifecycle.*
import io.wax911.challenge.core.component.viewmodel.AbstractViewModel
import io.wax911.challenge.domain.common.DataState
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.domain.landing.interactor.ILandingUseCase

class LandingViewModel(
    private val useCase: ILandingUseCase
) : AbstractViewModel<Credit.Landing>() {

    private val useCaseResult = MutableLiveData<DataState<Credit.Landing>>()

    override val model = Transformations.switchMap(useCaseResult) {
        it.data.asLiveData(viewModelScope.coroutineContext)
    }

    override val isLoading = Transformations.switchMap(useCaseResult) {
        it.isLoading.asLiveData(viewModelScope.coroutineContext)
    }

    override val error = Transformations.switchMap(useCaseResult) {
        it.error.asLiveData(viewModelScope.coroutineContext)
    }

    operator fun invoke() {
        val data = useCase(viewModelScope)
        useCaseResult.value = data
    }
}
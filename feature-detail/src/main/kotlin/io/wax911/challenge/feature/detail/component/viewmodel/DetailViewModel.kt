package io.wax911.challenge.feature.detail.component.viewmodel

import androidx.lifecycle.*
import io.wax911.challenge.core.component.viewmodel.AbstractViewModel
import io.wax911.challenge.domain.common.DataState
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.domain.detail.interactor.IDetailUseCase

class DetailViewModel(
    private val useCase: IDetailUseCase
) : AbstractViewModel<Credit.Detail>() {

    private val useCaseResult = MutableLiveData<DataState<Credit.Detail>>()

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
package io.wax911.challenge.data.detail.usecase

import io.wax911.challenge.domain.common.DataState
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.domain.detail.interactor.IDetailUseCase
import io.wax911.challenge.domain.detail.repository.IDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import timber.log.Timber

internal class DetailUseCase(
    private val repository: IDetailRepository,
    private val dispatcher: CoroutineDispatcher
) : IDetailUseCase {
    /**
     * Invokes the primary action for this use case
     *
     * @param scope Coroutine scope for launching
     * work that would otherwise be async or blocking
     *
     * @return [DataState]
     */
    override fun invoke(scope: CoroutineScope): DataState<Credit.Detail> {
        val errorFlow = MutableStateFlow<Throwable?>(null)
        val loadFlow = MutableStateFlow<Boolean?>(null)
        val dataFlow = MutableStateFlow<Credit.Detail?>(null)

        scope.launch(dispatcher) {
            runCatching {
                loadFlow.value = true
                dataFlow.value = repository.getDetailScore()
                loadFlow.value = false
            }.onFailure {
                Timber.e(it)
                errorFlow.value = it
            }
        }

        return DataState(
            data = dataFlow.filterNotNull(),
            isLoading = loadFlow.filterNotNull(),
            error = errorFlow.filterNotNull()
        )
    }
}
package io.wax911.challenge.data.landing.usecase

import io.wax911.challenge.domain.common.DataState
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.domain.landing.interactor.ILandingUseCase
import io.wax911.challenge.domain.landing.repository.ILandingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import timber.log.Timber

internal class LandingUseCase(
    private val repository: ILandingRepository,
    private val dispatcher: CoroutineDispatcher
) : ILandingUseCase {
    /**
     * Invokes the primary action for this use case
     *
     * @param scope Coroutine scope for launching
     * work that would otherwise be async or blocking
     *
     * @return [DataState]
     */
    override fun invoke(scope: CoroutineScope): DataState<Credit.Landing> {
        val errorFlow = MutableStateFlow<Throwable?>(null)
        val loadFlow = MutableStateFlow<Boolean?>(null)
        val dataFlow = MutableStateFlow<Credit.Landing?>(null)

        scope.launch(dispatcher) {
            runCatching {
                loadFlow.value = true
                dataFlow.value = repository.getLandingScore()
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
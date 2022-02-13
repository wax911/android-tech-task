package io.wax911.challenge.domain.detail.interactor

import io.wax911.challenge.domain.common.DataState
import io.wax911.challenge.domain.credit.enity.Credit
import kotlinx.coroutines.CoroutineScope

interface IDetailUseCase {

    /**
     * Invokes the primary action for this use case
     *
     * @param scope Coroutine scope for launching
     * work that would otherwise be async or blocking
     *
     * @return [DataState]
     */
    operator fun invoke(
        scope: CoroutineScope
    ) : DataState<Credit.Detail>
}
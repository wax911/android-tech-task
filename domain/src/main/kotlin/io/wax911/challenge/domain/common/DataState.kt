package io.wax911.challenge.domain.common

import kotlinx.coroutines.flow.Flow

/**
 * State container
 */
data class DataState<T>(
    val data: Flow<T>,
    val isLoading: Flow<Boolean>,
    val error: Flow<Throwable>
)
package io.wax911.challenge.data.detail.repository

import io.wax911.challenge.data.credit.source.CreditDataSource
import io.wax911.challenge.data.detail.converter.DetailConverter
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.domain.detail.repository.IDetailRepository
import kotlinx.coroutines.CoroutineScope

internal class DetailRepository(
    private val dataSource: CreditDataSource
) : IDetailRepository {
    override suspend fun getDetailScore(): Credit.Detail {
        // Introspect throws false positives for the the @Throw annotations
        @Suppress("BlockingMethodInNonBlockingContext")
        val result = dataSource()
        return DetailConverter.from(result)
    }
}
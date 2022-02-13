package io.wax911.challenge.data.landing.repository

import io.wax911.challenge.data.credit.source.CreditDataSource
import io.wax911.challenge.data.landing.converter.LandingConverter
import io.wax911.challenge.domain.credit.enity.Credit
import io.wax911.challenge.domain.landing.repository.ILandingRepository
import kotlinx.coroutines.CoroutineScope

internal class LandingRepository(
    private val dataSource: CreditDataSource
) : ILandingRepository {
    override suspend fun getLandingScore(): Credit.Landing {
        // Introspect throws false positives for the the @Throw annotations
        @Suppress("BlockingMethodInNonBlockingContext")
        val result = dataSource()
        return LandingConverter.from(result)
    }
}
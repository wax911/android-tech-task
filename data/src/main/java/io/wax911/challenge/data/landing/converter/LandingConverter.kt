package io.wax911.challenge.data.landing.converter

import io.wax911.challenge.data.core.mapper.AbstractMapper
import io.wax911.challenge.data.credit.entity.CreditEntity
import io.wax911.challenge.domain.credit.enity.Credit

internal object LandingConverter : AbstractMapper<CreditEntity, Credit.Landing>() {
    override val fromType: (source: CreditEntity) -> Credit.Landing = {
        Credit.Landing(
            score = it.creditReportInfo.score,
            maximum = it.creditReportInfo.maxScoreValue,
            minimum = it.creditReportInfo.minScoreValue
        )
    }
}
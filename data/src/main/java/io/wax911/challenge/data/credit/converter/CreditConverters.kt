package io.wax911.challenge.data.credit.converter

import io.wax911.challenge.data.core.mapper.AbstractMapper
import io.wax911.challenge.data.credit.entity.CreditEntity
import io.wax911.challenge.data.credit.model.CreditModel
import java.util.concurrent.TimeUnit

internal object CreditConverter : AbstractMapper<CreditModel, CreditEntity>() {

    private fun generateExpiryTime(): Long {
        val currentTime = System.currentTimeMillis()
        val offset = TimeUnit.MINUTES.convert(
            15,
            TimeUnit.MILLISECONDS
        )
        return currentTime + offset
    }

    override val fromType: (source: CreditModel) -> CreditEntity = {
        val reportInfo = it.creditReportInfo
        val summary = it.coachingSummary

        CreditEntity(
            id = it.creditReportInfo.clientRef,
            expiresAt = generateExpiryTime(),
            accountIDVStatus = it.accountIDVStatus,
            creditReportInfo = CreditEntity.ReportInfo(
                score = reportInfo.score,
                scoreBand = reportInfo.scoreBand,
                status = reportInfo.status,
                maxScoreValue = reportInfo.maxScoreValue,
                minScoreValue = reportInfo.minScoreValue,
                monthsSinceLastDefaulted = reportInfo.monthsSinceLastDefaulted,
                hasEverDefaulted = reportInfo.hasEverDefaulted,
                monthsSinceLastDelinquent = reportInfo.monthsSinceLastDelinquent,
                hasEverBeenDelinquent = reportInfo.hasEverBeenDelinquent,
                percentageCreditUsed = reportInfo.percentageCreditUsed,
                percentageCreditUsedDirectionFlag = reportInfo.percentageCreditUsedDirectionFlag,
                changedScore = reportInfo.changedScore,
                currentShortTermDebt = reportInfo.currentShortTermDebt,
                currentShortTermNonPromotionalDebt = reportInfo.currentShortTermNonPromotionalDebt,
                currentShortTermCreditLimit = reportInfo.currentShortTermCreditLimit,
                currentShortTermCreditUtilisation = reportInfo.currentShortTermCreditUtilisation,
                changeInShortTermDebt = reportInfo.changeInShortTermDebt,
                currentLongTermDebt = reportInfo.currentLongTermDebt,
                currentLongTermNonPromotionalDebt = reportInfo.currentLongTermNonPromotionalDebt,
                currentLongTermCreditLimit = reportInfo.currentLongTermCreditLimit,
                currentLongTermCreditUtilisation = reportInfo.currentLongTermCreditUtilisation,
                changeInLongTermDebt = reportInfo.changeInLongTermDebt,
                numPositiveScoreFactors = reportInfo.numPositiveScoreFactors,
                numNegativeScoreFactors = reportInfo.numNegativeScoreFactors,
                equifaxScoreBand = reportInfo.equifaxScoreBand,
                equifaxScoreBandDescription = reportInfo.equifaxScoreBandDescription,
                daysUntilNextReport = reportInfo.daysUntilNextReport,
            ),
            dashboardStatus = it.dashboardStatus,
            personaType = it.personaType,
            coachingSummary = CreditEntity.CoachingSummary(
                activeTodo = summary.activeTodo,
                activeChat = summary.activeChat,
                numberOfTodoItems = summary.numberOfTodoItems,
                numberOfCompletedTodoItems = summary.numberOfCompletedTodoItems,
                selected = summary.selected,
            ),
            augmentedCreditScore = it.augmentedCreditScore,
        )
    }
}
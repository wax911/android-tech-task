package io.wax911.challenge.data.detail.converter

import io.wax911.challenge.data.core.mapper.AbstractMapper
import io.wax911.challenge.data.credit.entity.CreditEntity
import io.wax911.challenge.domain.credit.enity.Credit

internal object DetailConverter : AbstractMapper<CreditEntity, Credit.Detail>() {

    private fun todoItemsProgress(current: Int, total: Int): Int {
        return runCatching { ((total / current) * 100) }
            .getOrElse { 0 }
    }

    override val fromType: (source: CreditEntity) -> Credit.Detail = {
        Credit.Detail(
            score = it.creditReportInfo.score,
            maximum = it.creditReportInfo.maxScoreValue,
            minimum = it.creditReportInfo.minScoreValue,
            accountIDVStatus = it.accountIDVStatus,
            dashboardStatus = it.dashboardStatus,
            personaType = it.personaType,
            info = Credit.Detail.Info(
                monthsSinceLastDefaulted = it.creditReportInfo.monthsSinceLastDefaulted,
                hasEverDefaulted = it.creditReportInfo.hasEverDefaulted,
                daysUntilNextReport = it.creditReportInfo.daysUntilNextReport,
                percentageCreditUsed = it.creditReportInfo.percentageCreditUsed,
            ),
            summary = Credit.Detail.Summary(
                activeTodo = it.coachingSummary.activeTodo,
                activeChat = it.coachingSummary.activeChat,
                percentageTodoCompleted = todoItemsProgress(
                    it.coachingSummary.numberOfTodoItems,
                    it.coachingSummary.numberOfCompletedTodoItems
                )
            ),
        )
    }
}

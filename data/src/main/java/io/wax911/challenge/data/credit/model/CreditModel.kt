package io.wax911.challenge.data.credit.model

import io.wax911.challenge.domain.credit.enums.Persona
import io.wax911.challenge.domain.credit.enums.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CreditModel(
    @SerialName("accountIDVStatus") val accountIDVStatus: Status,
    @SerialName("creditReportInfo") val creditReportInfo: CreditReportInfo,
    @SerialName("dashboardStatus") val dashboardStatus: Status,
    @SerialName("personaType") val personaType: Persona,
    @SerialName("coachingSummary") val coachingSummary: CoachingSummary,
    @SerialName("augmentedCreditScore") val augmentedCreditScore: String?,
) {
    @Serializable
    data class CreditReportInfo(
        @SerialName("score") val score: Int,
        @SerialName("scoreBand") val scoreBand: Int,
        @SerialName("clientRef") val clientRef: String,
        @SerialName("status") val status: Status,
        @SerialName("maxScoreValue") val maxScoreValue: Int,
        @SerialName("minScoreValue") val minScoreValue: Int,
        @SerialName("monthsSinceLastDefaulted") val monthsSinceLastDefaulted: Int,
        @SerialName("hasEverDefaulted") val hasEverDefaulted: Boolean,
        @SerialName("monthsSinceLastDelinquent") val monthsSinceLastDelinquent: Int,
        @SerialName("hasEverBeenDelinquent") val hasEverBeenDelinquent: Boolean,
        @SerialName("percentageCreditUsed") val percentageCreditUsed: Int,
        @SerialName("percentageCreditUsedDirectionFlag") val percentageCreditUsedDirectionFlag: Int,
        @SerialName("changedScore") val changedScore: Int,
        @SerialName("currentShortTermDebt") val currentShortTermDebt: Int,
        @SerialName("currentShortTermNonPromotionalDebt") val currentShortTermNonPromotionalDebt: Int,
        @SerialName("currentShortTermCreditLimit") val currentShortTermCreditLimit: Int,
        @SerialName("currentShortTermCreditUtilisation") val currentShortTermCreditUtilisation: Int,
        @SerialName("changeInShortTermDebt") val changeInShortTermDebt: Int,
        @SerialName("currentLongTermDebt") val currentLongTermDebt: Int,
        @SerialName("currentLongTermNonPromotionalDebt") val currentLongTermNonPromotionalDebt: Int,
        @SerialName("currentLongTermCreditLimit") val currentLongTermCreditLimit: Int?,
        @SerialName("currentLongTermCreditUtilisation") val currentLongTermCreditUtilisation: Int?,
        @SerialName("changeInLongTermDebt") val changeInLongTermDebt: Int,
        @SerialName("numPositiveScoreFactors") val numPositiveScoreFactors: Int,
        @SerialName("numNegativeScoreFactors") val numNegativeScoreFactors: Int,
        @SerialName("equifaxScoreBand") val equifaxScoreBand: Int,
        @SerialName("equifaxScoreBandDescription") val equifaxScoreBandDescription: String,
        @SerialName("daysUntilNextReport") val daysUntilNextReport: Int,
    )

    @Serializable
    data class CoachingSummary(
        @SerialName("activeTodo") val activeTodo: Boolean,
        @SerialName("activeChat") val activeChat: Boolean,
        @SerialName("numberOfTodoItems") val numberOfTodoItems: Int,
        @SerialName("numberOfCompletedTodoItems") val numberOfCompletedTodoItems: Int,
        @SerialName("selected") val selected: Boolean,
    )
}
package io.wax911.challenge.data.credit.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import io.wax911.challenge.domain.credit.enums.Persona
import io.wax911.challenge.domain.credit.enums.Status

@Entity(
    tableName = "credit",
    primaryKeys = ["id"]
)
internal data class CreditEntity(
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "expires_at") val expiresAt: Long,
    @ColumnInfo(name = "account_idv_status") val accountIDVStatus: Status,
    @Embedded(prefix = "credit_") val creditReportInfo: ReportInfo,
    @ColumnInfo(name = "dashboard_status") val dashboardStatus: Status,
    @ColumnInfo(name = "persona_type") val personaType: Persona,
    @Embedded(prefix = "credit_") val coachingSummary: CoachingSummary,
    @ColumnInfo(name = "augmented_credit_score") val augmentedCreditScore: String?,
) {
    data class ReportInfo(
        @ColumnInfo(name ="score") val score: Int,
        @ColumnInfo(name ="score_band") val scoreBand: Int,
        @ColumnInfo(name ="status") val status: Status,
        @ColumnInfo(name ="max_score_value") val maxScoreValue: Int,
        @ColumnInfo(name ="min_score_value") val minScoreValue: Int,
        @ColumnInfo(name ="months_since_last_defaulted") val monthsSinceLastDefaulted: Int,
        @ColumnInfo(name ="has_ever_defaulted") val hasEverDefaulted: Boolean,
        @ColumnInfo(name ="months_since_last_delinquent") val monthsSinceLastDelinquent: Int,
        @ColumnInfo(name ="has_ever_been_delinquent") val hasEverBeenDelinquent: Boolean,
        @ColumnInfo(name ="percentage_credit_used") val percentageCreditUsed: Int,
        @ColumnInfo(name ="percentage_credit_used_direction_flag") val percentageCreditUsedDirectionFlag: Int,
        @ColumnInfo(name ="changed_score") val changedScore: Int,
        @ColumnInfo(name ="current_short_term_debt") val currentShortTermDebt: Int,
        @ColumnInfo(name ="current_short_term_non_promotional_debt") val currentShortTermNonPromotionalDebt: Int,
        @ColumnInfo(name ="current_short_term_credit_limit") val currentShortTermCreditLimit: Int,
        @ColumnInfo(name ="current_short_term_credit_utilisation") val currentShortTermCreditUtilisation: Int,
        @ColumnInfo(name ="changeInShort_term_debt") val changeInShortTermDebt: Int,
        @ColumnInfo(name ="current_long_term_debt") val currentLongTermDebt: Int,
        @ColumnInfo(name ="current_long_term_non_promotional_debt") val currentLongTermNonPromotionalDebt: Int,
        @ColumnInfo(name ="current_long_term_credit_limit") val currentLongTermCreditLimit: Int?,
        @ColumnInfo(name ="current_long_term_credit_utilisation") val currentLongTermCreditUtilisation: Int?,
        @ColumnInfo(name ="change_in_long_term_debt") val changeInLongTermDebt: Int,
        @ColumnInfo(name ="num_positive_score_factors") val numPositiveScoreFactors: Int,
        @ColumnInfo(name ="num_negative_score_factors") val numNegativeScoreFactors: Int,
        @ColumnInfo(name ="equifax_score_band") val equifaxScoreBand: Int,
        @ColumnInfo(name ="equifax_score_band_description") val equifaxScoreBandDescription: String,
        @ColumnInfo(name ="days_until_next_report") val daysUntilNextReport: Int,
    )

    data class CoachingSummary(
        @ColumnInfo(name = "active_todo") val activeTodo: Boolean,
        @ColumnInfo(name = "active_chat") val activeChat: Boolean,
        @ColumnInfo(name = "number_of_todo_items") val numberOfTodoItems: Int,
        @ColumnInfo(name = "number_of_completed_todo_items") val numberOfCompletedTodoItems: Int,
        @ColumnInfo(name = "selected") val selected: Boolean,
    )
}
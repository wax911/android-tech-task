package io.wax911.challenge.domain.credit.enity

import io.wax911.challenge.domain.credit.enums.Persona
import io.wax911.challenge.domain.credit.enums.Status

sealed class Credit {
    abstract val score: Int
    abstract val maximum: Int
    abstract val minimum: Int

    data class Landing(
        override val score: Int,
        override val maximum: Int,
        override val minimum: Int
    ) : Credit()

    data class Detail(
        override val score: Int,
        override val maximum: Int,
        override val minimum: Int,
        val accountIDVStatus: Status,
        val dashboardStatus: Status,
        val personaType: Persona,
        val info: Info,
        val summary: Summary
    ) : Credit() {

        data class Info(
            val monthsSinceLastDefaulted: Int,
            val hasEverDefaulted: Boolean,
            val daysUntilNextReport: Int,
            val percentageCreditUsed: Int
        )

        data class Summary(
            val activeTodo: Boolean,
            val activeChat: Boolean,
            val percentageTodoCompleted: Int,
        )
    }
}
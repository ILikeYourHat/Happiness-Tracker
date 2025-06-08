package io.github.ilikeyourhat.happinesstracker.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HappinessDatabase {

    private val entries = mutableMapOf<LocalDate, HappinessLevel>()

    suspend fun getHappinessLevelForToday(): HappinessLevel? {
        return entries[today()]
    }

    suspend fun saveHappinessLevelForToday(level: HappinessLevel?) {
        val today = today()
        if (level == null) {
            entries.remove(today)
        } else {
            entries[today] = level
        }
    }

    suspend fun getHappinessLevelFlow(): List<Pair<LocalDate, HappinessLevel>> {
        return entries.toList().sortedBy { it.first }
    }

    private fun today(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}
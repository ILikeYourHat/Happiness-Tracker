package io.github.ilikeyourhat.happinesstracker.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object HappinessDatabase {

    private val entries = mutableMapOf<LocalDate, HappinessLevel>()

    suspend fun getHappinessLevelForToday(): HappinessEntry? {
        val today = today()
        val level = entries[today]
        return if (level != null) {
            HappinessEntry(today, level)
        } else {
            null
        }
    }

    suspend fun saveHappinessLevelForToday(level: HappinessLevel?) {
        val today = today()
        if (level == null) {
            entries.remove(today)
        } else {
            entries[today] = level
        }
    }

    suspend fun getHappinessLevelHistory(): List<HappinessEntry> {
        return entries.toList()
            .map { (date, level) -> HappinessEntry(date, level) }
            .sortedBy { it.date }
    }

    private fun today(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}
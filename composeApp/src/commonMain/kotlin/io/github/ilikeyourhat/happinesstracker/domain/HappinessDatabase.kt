package io.github.ilikeyourhat.happinesstracker.domain

import io.github.ilikeyourhat.happinesstracker.domain.db.HappinessLevelEntity
import io.github.ilikeyourhat.happinesstracker.domain.db.getRoomDatabase
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object HappinessDatabase {

    private val roomDatabase = getRoomDatabase()

    suspend fun getHappinessLevelForToday(): HappinessEntry? {
        val today = today()
        return roomDatabase.getDao().getAll()
            .firstOrNull { it.date == today.toString() }
            ?.let { HappinessEntry(LocalDate.parse(it.date), HappinessLevel.VERY_UNHAPPY) }
    }

    suspend fun saveHappinessLevelForToday(level: HappinessLevel?) {
        val today = today()
        roomDatabase.getDao().insert(
            HappinessLevelEntity(
                date = today.toString(),
                level = level?.name ?: "sad"
            )
        )
    }

    suspend fun getHappinessLevelHistory(): List<HappinessEntry> {
        return roomDatabase.getDao().getAll()
            .map { (_, date, level) -> HappinessEntry(LocalDate.parse(date), HappinessLevel.VERY_UNHAPPY) }
            .sortedBy { it.date }
    }

    private fun today(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}
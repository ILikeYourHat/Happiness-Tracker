package io.github.ilikeyourhat.happinesstracker.domain

import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.HappinessLevelEntity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Inject
class HappinessDatabase(
    private val roomDatabase: AppDatabase
) {

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

    @OptIn(ExperimentalTime::class)
    private fun today(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}
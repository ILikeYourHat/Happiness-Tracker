package io.github.ilikeyourhat.happinesstracker.domain

import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.db.HappinessDao
import io.github.ilikeyourhat.happinesstracker.domain.db.HappinessLevelEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.collections.map
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Inject
class HappinessDatabase(
    private val happinessDao: HappinessDao
) {

    suspend fun getHappinessLevelForToday(): HappinessEntry? {
        val today = today()
        return happinessDao.getByDate(today)
            ?.let { HappinessEntry(it.date, it.level) }
    }

    fun getHappinessLevelForTodayFlow(): Flow<HappinessEntry?> {
        val today = today()
        return happinessDao.observe()
            .map { levels ->
                levels.singleOrNull { it.date == today }
                    ?.let { HappinessEntry(it.date, it.level) }
            }
    }

    suspend fun saveHappinessLevelForToday(level: HappinessLevel?) {
        val today = today()
        if (level == null) {
            happinessDao.clearByDate(today)
        } else {
            happinessDao.insert(
                HappinessLevelEntity(
                    date = today,
                    level = level
                )
            )
        }
    }

    fun getHappinessLevelHistoryUpdates(): Flow<List<HappinessEntry>> {
        return happinessDao.observe()
            .map { entities ->
                entities.map { entity -> HappinessEntry(entity.date, entity.level) }
                    .sortedBy { it.date }
            }
    }

    @OptIn(ExperimentalTime::class)
    private fun today(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }
}

package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.TypeConverter
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel

class HappinessConverters {

    private val happinessLevelMap = mapOf(
        HappinessLevel.VERY_HAPPY to 5,
        HappinessLevel.HAPPY to 4,
        HappinessLevel.NEUTRAL to 3,
        HappinessLevel.UNHAPPY to 2,
        HappinessLevel.VERY_UNHAPPY to 1
    )

    @TypeConverter
    fun intToHappinessLevel(value: Int?): HappinessLevel? {
        return happinessLevelMap.entries
            .find { it.value == value }
            ?.key
    }

    @TypeConverter
    fun happinessLevelToInt(level: HappinessLevel?): Int? {
        return level?.let { happinessLevelMap[it] }
    }
}

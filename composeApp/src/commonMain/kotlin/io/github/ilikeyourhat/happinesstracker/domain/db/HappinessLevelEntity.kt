package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel
import kotlinx.datetime.LocalDate

@Entity
data class HappinessLevelEntity(
    @PrimaryKey
    val date: LocalDate,
    val level: HappinessLevel
)

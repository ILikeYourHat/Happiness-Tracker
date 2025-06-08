package io.github.ilikeyourhat.happinesstracker.domain

import kotlinx.datetime.LocalDate

data class HappinessEntry (
    val date: LocalDate,
    val happinessLevel: HappinessLevel
)

package io.github.ilikeyourhat.happinesstracker.ui.stats

import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel

data class StatsUiState(
    val summary: List<Pair<HappinessLevel, Int>> = emptyList()
)
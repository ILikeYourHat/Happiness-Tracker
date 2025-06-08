package io.github.ilikeyourhat.happinesstracker.ui.history

import io.github.ilikeyourhat.happinesstracker.domain.HappinessEntry

data class HistoryUiState(
    val historyItems: List<HappinessEntry> = emptyList()
)
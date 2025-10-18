package io.github.ilikeyourhat.happinesstracker.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.HappinessDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Inject
class HistoryViewModel(
    database: HappinessDatabase
) : ViewModel() {

    val uiState = database.getHappinessLevelHistoryUpdates()
        .map { entries -> entries.sortedByDescending { it.date } }
        .map { historyItems -> HistoryUiState(historyItems) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HistoryUiState(),
        )
}

package io.github.ilikeyourhat.happinesstracker.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.HappinessDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Inject
class StatsViewModel(
    database: HappinessDatabase
) : ViewModel() {

    val uiState = database.getHappinessLevelHistoryUpdates()
        .map { items ->
            items.groupBy { it.happinessLevel }
                .map { it.key to it.value.size }
                .sortedBy { it.first }
        }
        .map { StatsUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StatsUiState(),
        )
}

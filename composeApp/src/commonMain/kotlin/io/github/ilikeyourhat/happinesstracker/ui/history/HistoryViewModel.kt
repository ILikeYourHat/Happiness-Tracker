package io.github.ilikeyourhat.happinesstracker.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.HappinessDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Inject
class HistoryViewModel(
    private val database: HappinessDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState = _uiState.asStateFlow()

    fun onResume() {
        viewModelScope.launch {
            val historyItems = database.getHappinessLevelHistory()
            _uiState.value = HistoryUiState(historyItems = historyItems)
        }
    }
}

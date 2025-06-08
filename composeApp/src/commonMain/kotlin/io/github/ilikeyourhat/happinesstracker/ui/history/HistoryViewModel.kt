package io.github.ilikeyourhat.happinesstracker.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.ilikeyourhat.happinesstracker.domain.HappinessDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    private val happinessDatabase = HappinessDatabase

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState = _uiState.asStateFlow()

    fun onResume() {
        viewModelScope.launch {
            val historyItems = happinessDatabase.getHappinessLevelHistory()
            _uiState.value = HistoryUiState(historyItems = historyItems)
        }
    }
}

package io.github.ilikeyourhat.happinesstracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.HappinessDatabase
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Inject
class HomeViewModel(
    private val database: HappinessDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    fun onResume() {
        viewModelScope.launch {
            val entry = database.getHappinessLevelForToday()
            _uiState.value = HomeUiState(selectedHappinessLevel = entry?.happinessLevel)
        }
    }

    fun onHappinessLevelClicked(level: HappinessLevel) {
        val currentLevel = _uiState.value.selectedHappinessLevel
        val updatedLevel = if (currentLevel == level) null else level
        _uiState.value = HomeUiState(selectedHappinessLevel = updatedLevel)
        viewModelScope.launch {
            database.saveHappinessLevelForToday(updatedLevel)
        }
    }
}

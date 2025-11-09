package io.github.ilikeyourhat.happinesstracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.HappinessDatabase
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Inject
class HomeViewModel(
    private val database: HappinessDatabase
) : ViewModel() {

    val uiState = database.getHappinessLevelForTodayFlow()
        .map { HomeUiState(selectedHappinessLevel = it?.happinessLevel) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState(),
        )

    fun onHappinessLevelClicked(level: HappinessLevel) {
        val currentLevel = uiState.value.selectedHappinessLevel
        val updatedLevel = if (currentLevel == level) null else level
        viewModelScope.launch {
            database.saveHappinessLevelForToday(updatedLevel)
        }
    }
}

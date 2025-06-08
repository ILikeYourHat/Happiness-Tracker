package io.github.ilikeyourhat.happinesstracker.ui.home

import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel

data class HomeUiState(
    val selectedHappinessLevel: HappinessLevel? = null
)

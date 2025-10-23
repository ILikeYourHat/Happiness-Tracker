package io.github.ilikeyourhat.happinesstracker.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Suppress("MagicNumber")
enum class HappinessLevel(
    val displayName: String,
    val icon: ImageVector,
    val color: Color
) {
    VERY_HAPPY(
        displayName = "Great",
        icon = Icons.Filled.SentimentVerySatisfied,
        color = Color(0xFF4CAF50)
    ),
    HAPPY(
        displayName = "Good",
        icon = Icons.Filled.SentimentSatisfied,
        color = Color(0xFF8BC34A)
    ),
    NEUTRAL(
        displayName = "Okay",
        icon = Icons.Filled.SentimentNeutral,
        color = Color(0xFFFFC107)
    ),
    UNHAPPY(
        displayName = "Bad",
        icon = Icons.Filled.SentimentDissatisfied,
        color = Color(0xFFFF9800)
    ),
    VERY_UNHAPPY(
        displayName = "Terrible",
        icon = Icons.Filled.SentimentVeryDissatisfied,
        color = Color(0xFFF44336)
    )
}

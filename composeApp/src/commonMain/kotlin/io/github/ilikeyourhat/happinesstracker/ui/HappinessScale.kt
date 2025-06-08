package io.github.ilikeyourhat.happinesstracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel

@Composable
fun HappinessScale(
    onHappinessSelected: (HappinessLevel) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HappinessButton(
            icon = Icons.Filled.SentimentVerySatisfied,
            description = "Great",
            backgroundColor = Color(0xFF4CAF50),
            onClick = { onHappinessSelected(HappinessLevel.VERY_HAPPY) }
        )
        HappinessButton(
            icon = Icons.Filled.SentimentSatisfied,
            description = "Good",
            backgroundColor = Color(0xFF8BC34A),
            onClick = { onHappinessSelected(HappinessLevel.HAPPY) }
        )
        HappinessButton(
            icon = Icons.Filled.SentimentNeutral,
            description = "Okay",
            backgroundColor = Color(0xFFFFC107),
            onClick = { onHappinessSelected(HappinessLevel.NEUTRAL) }
        )
        HappinessButton(
            icon = Icons.Filled.SentimentDissatisfied,
            description = "Bad",
            backgroundColor = Color(0xFFFF9800),
            onClick = { onHappinessSelected(HappinessLevel.UNHAPPY) }
        )
        HappinessButton(
            icon = Icons.Filled.SentimentVeryDissatisfied,
            description = "Terrible",
            backgroundColor = Color(0xFFF44336),
            onClick = { onHappinessSelected(HappinessLevel.VERY_UNHAPPY) }
        )
    }
}

@Composable
fun HappinessButton(
    icon: ImageVector,
    description: String,
    backgroundColor: Color,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.background(
            color = backgroundColor,
            shape = Shapes().medium
        )
    ) {
        Icon(icon, description)
    }
}
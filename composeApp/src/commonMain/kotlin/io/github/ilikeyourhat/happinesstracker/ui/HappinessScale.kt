package io.github.ilikeyourhat.happinesstracker.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel

@Composable
fun HappinessScale(
    selectedLevel: HappinessLevel? = null,
    onHappinessSelected: (HappinessLevel) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HappinessButton(
            level = HappinessLevel.VERY_HAPPY,
            selected = selectedLevel == HappinessLevel.VERY_HAPPY,
            onClick = { onHappinessSelected(HappinessLevel.VERY_HAPPY) }
        )
        HappinessButton(
            level = HappinessLevel.HAPPY,
            selected = selectedLevel == HappinessLevel.HAPPY,
            onClick = { onHappinessSelected(HappinessLevel.HAPPY) }
        )
        HappinessButton(
            level = HappinessLevel.NEUTRAL,
            selected = selectedLevel == HappinessLevel.NEUTRAL,
            onClick = { onHappinessSelected(HappinessLevel.NEUTRAL) }
        )
        HappinessButton(
            level = HappinessLevel.UNHAPPY,
            selected = selectedLevel == HappinessLevel.UNHAPPY,
            onClick = { onHappinessSelected(HappinessLevel.UNHAPPY) }
        )
        HappinessButton(
            level = HappinessLevel.VERY_UNHAPPY,
            selected = selectedLevel == HappinessLevel.VERY_UNHAPPY,
            onClick = { onHappinessSelected(HappinessLevel.VERY_UNHAPPY) }
        )
    }
}

@Composable
fun HappinessButton(
    level: HappinessLevel,
    selected: Boolean,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .background(
                color = level.color,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                border = getBorderWhenSelected(selected),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Icon(level.icon, level.displayName)
    }
}

fun getBorderWhenSelected(selected: Boolean): BorderStroke {
    return if (selected) {
        BorderStroke(2.dp, Color.Black)
    } else {
        BorderStroke(0.dp, Color.Transparent)
    }
}

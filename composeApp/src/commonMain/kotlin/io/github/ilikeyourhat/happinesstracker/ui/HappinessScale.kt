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
import org.jetbrains.compose.resources.painterResource

@Composable
fun HappinessScale(
    selectedLevel: HappinessLevel?,
    onHappinessSelect: (HappinessLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        HappinessButton(
            level = HappinessLevel.VERY_HAPPY,
            selected = selectedLevel == HappinessLevel.VERY_HAPPY,
            onClick = { onHappinessSelect(HappinessLevel.VERY_HAPPY) }
        )
        HappinessButton(
            level = HappinessLevel.HAPPY,
            selected = selectedLevel == HappinessLevel.HAPPY,
            onClick = { onHappinessSelect(HappinessLevel.HAPPY) }
        )
        HappinessButton(
            level = HappinessLevel.NEUTRAL,
            selected = selectedLevel == HappinessLevel.NEUTRAL,
            onClick = { onHappinessSelect(HappinessLevel.NEUTRAL) }
        )
        HappinessButton(
            level = HappinessLevel.UNHAPPY,
            selected = selectedLevel == HappinessLevel.UNHAPPY,
            onClick = { onHappinessSelect(HappinessLevel.UNHAPPY) }
        )
        HappinessButton(
            level = HappinessLevel.VERY_UNHAPPY,
            selected = selectedLevel == HappinessLevel.VERY_UNHAPPY,
            onClick = { onHappinessSelect(HappinessLevel.VERY_UNHAPPY) }
        )
    }
}

@Composable
fun HappinessButton(
    level: HappinessLevel,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(
                color = level.color,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                border = getBorderWhenSelected(selected),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Icon(painterResource(level.icon), level.displayName)
    }
}

fun getBorderWhenSelected(selected: Boolean): BorderStroke {
    return if (selected) {
        BorderStroke(2.dp, Color.Black)
    } else {
        BorderStroke(0.dp, Color.Transparent)
    }
}

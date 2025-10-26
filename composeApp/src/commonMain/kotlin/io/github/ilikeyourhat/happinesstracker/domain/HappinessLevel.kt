package io.github.ilikeyourhat.happinesstracker.domain

import androidx.compose.ui.graphics.Color
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.ic_happy
import happinesstracker.composeapp.generated.resources.ic_neutral
import happinesstracker.composeapp.generated.resources.ic_unhappy
import happinesstracker.composeapp.generated.resources.ic_very_happy
import happinesstracker.composeapp.generated.resources.ic_very_unhappy
import org.jetbrains.compose.resources.DrawableResource

@Suppress("MagicNumber")
enum class HappinessLevel(
    val displayName: String,
    val icon: DrawableResource,
    val color: Color
) {
    VERY_HAPPY(
        displayName = "Great",
        icon = Res.drawable.ic_very_happy,
        color = Color(0xFF4CAF50)
    ),
    HAPPY(
        displayName = "Good",
        icon = Res.drawable.ic_happy,
        color = Color(0xFF8BC34A)
    ),
    NEUTRAL(
        displayName = "Okay",
        icon = Res.drawable.ic_neutral,
        color = Color(0xFFFFC107)
    ),
    UNHAPPY(
        displayName = "Bad",
        icon = Res.drawable.ic_unhappy,
        color = Color(0xFFFF9800)
    ),
    VERY_UNHAPPY(
        displayName = "Terrible",
        icon = Res.drawable.ic_very_unhappy,
        color = Color(0xFFF44336)
    )
}

package io.github.ilikeyourhat.happinesstracker.domain

import androidx.compose.ui.graphics.Color
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.ic_mood_happy
import happinesstracker.composeapp.generated.resources.ic_mood_neutral
import happinesstracker.composeapp.generated.resources.ic_mood_unhappy
import happinesstracker.composeapp.generated.resources.ic_mood_very_happy
import happinesstracker.composeapp.generated.resources.ic_mood_very_unhappy
import happinesstracker.composeapp.generated.resources.mood_happy
import happinesstracker.composeapp.generated.resources.mood_neutral
import happinesstracker.composeapp.generated.resources.mood_unhappy
import happinesstracker.composeapp.generated.resources.mood_very_happy
import happinesstracker.composeapp.generated.resources.mood_very_unhappy
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Suppress("MagicNumber")
enum class HappinessLevel(
    val displayName: StringResource,
    val icon: DrawableResource,
    val color: Color
) {
    VERY_HAPPY(
        displayName = Res.string.mood_very_happy,
        icon = Res.drawable.ic_mood_very_happy,
        color = Color(0xFF4CAF50)
    ),
    HAPPY(
        displayName = Res.string.mood_happy,
        icon = Res.drawable.ic_mood_happy,
        color = Color(0xFF8BC34A)
    ),
    NEUTRAL(
        displayName = Res.string.mood_neutral,
        icon = Res.drawable.ic_mood_neutral,
        color = Color(0xFFFFC107)
    ),
    UNHAPPY(
        displayName = Res.string.mood_unhappy,
        icon = Res.drawable.ic_mood_unhappy,
        color = Color(0xFFFF9800)
    ),
    VERY_UNHAPPY(
        displayName = Res.string.mood_very_unhappy,
        icon = Res.drawable.ic_mood_very_unhappy,
        color = Color(0xFFF44336)
    )
}

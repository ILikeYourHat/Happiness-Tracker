package io.github.ilikeyourhat.happinesstracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MoodButton(
                icon = Icons.Filled.SentimentVerySatisfied,
                description = "Great",
                backgroundColor = Color.Green
            )
            MoodButton(
                icon = Icons.Filled.SentimentSatisfied,
                description = "Good",
                backgroundColor = Color.Green
            )
            MoodButton(
                icon = Icons.Filled.SentimentNeutral,
                description = "Okay",
                backgroundColor = Color.Yellow
            )
            MoodButton(
                icon = Icons.Filled.SentimentDissatisfied,
                description = "Bad",
                backgroundColor = Color.Magenta
            )
            MoodButton(
                icon = Icons.Filled.SentimentVeryDissatisfied,
                description = "Awful",
                backgroundColor = Color.Red
            )
        }
    }
}

@Composable
private fun MoodButton(
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

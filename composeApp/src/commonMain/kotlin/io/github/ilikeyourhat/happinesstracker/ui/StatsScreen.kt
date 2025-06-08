package io.github.ilikeyourhat.happinesstracker.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun StatsScreen() {
    Text(
        text = "Stats Screen",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
    )
}
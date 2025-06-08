package io.github.ilikeyourhat.happinesstracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = stringResource(Res.string.title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        HappinessScale(
            onHappinessSelected = { level ->
                println("Selected happiness level: $level")
            }
        )
    }
}

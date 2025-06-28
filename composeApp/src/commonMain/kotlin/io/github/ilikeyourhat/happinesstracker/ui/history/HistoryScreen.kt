package io.github.ilikeyourhat.happinesstracker.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.ilikeyourhat.happinesstracker.di.AppGraph

@Composable
fun HistoryScreen(appGraph: AppGraph) {
    val viewModel = viewModel { appGraph.historyViewModel }
    val state by viewModel.uiState.collectAsState()
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.onResume()
    }
    HistoryScreen(state)
}

@Composable
fun HistoryScreen(uiState: HistoryUiState) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "History Screen",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
        ) {
            items(uiState.historyItems.size) { index ->
                val item = uiState.historyItems[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = item.happinessLevel.color,
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(item.happinessLevel.icon, null)
                    Text(
                        text = item.date.toString(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = item.happinessLevel.displayName,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        }
    }
}

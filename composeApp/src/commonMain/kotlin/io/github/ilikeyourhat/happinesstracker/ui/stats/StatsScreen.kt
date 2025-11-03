package io.github.ilikeyourhat.happinesstracker.ui.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.stats_empty_message
import happinesstracker.composeapp.generated.resources.stats_title
import io.github.ilikeyourhat.happinesstracker.di.AppGraph
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel
import io.github.ilikeyourhat.happinesstracker.ui.PieChart
import io.github.ilikeyourhat.happinesstracker.ui.PieChartData
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StatsScreen(
    appGraph: AppGraph,
    viewModel: StatsViewModel = viewModel { appGraph.statsViewModel }
) {
    val state by viewModel.uiState.collectAsState()
    StatsScreen(state)
}

@Composable
private fun StatsScreen(state: StatsUiState) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(Res.string.stats_title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
        )
        Box(
            modifier = Modifier.weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (state.summary.isEmpty()) {
                Text(
                    text = stringResource(Res.string.stats_empty_message),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            } else {
                PieChart(
                    entries = state.summary.map {
                        PieChartData(
                            it.first.name,
                            it.second,
                            it.first.color
                        )
                    },
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun StatsScreenPreview_Empty() {
    MaterialTheme {
        StatsScreen(StatsUiState())
    }
}

@Preview
@Composable
private fun StatsScreenPreview_WithData() {
    MaterialTheme {
        StatsScreen(
            StatsUiState(
                summary = listOf(
                    HappinessLevel.HAPPY to 5,
                    HappinessLevel.NEUTRAL to 3,
                    HappinessLevel.VERY_UNHAPPY to 2
                )
            )
        )
    }
}

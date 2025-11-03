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
import androidx.lifecycle.viewmodel.compose.viewModel
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.history_title
import io.github.ilikeyourhat.happinesstracker.di.AppGraph
import io.github.ilikeyourhat.happinesstracker.domain.HappinessEntry
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HistoryScreen(
    appGraph: AppGraph,
    viewModel: HistoryViewModel = viewModel { appGraph.historyViewModel }
) {
    val state by viewModel.uiState.collectAsState()
    HistoryScreen(state)
}

@Composable
fun HistoryScreen(
    uiState: HistoryUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.history_title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
                    Icon(painterResource(item.happinessLevel.icon), null)
                    Text(
                        text = item.date.toString(),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(item.happinessLevel.displayName),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview_Full() {
    MaterialTheme {
        HistoryScreen(
            HistoryUiState(
                historyItems = listOf(
                    HappinessEntry(
                        date = LocalDate.parse("2024-06-01"),
                        happinessLevel = HappinessLevel.VERY_HAPPY
                    ),
                    HappinessEntry(
                        date = LocalDate.parse("2024-06-02"),
                        happinessLevel = HappinessLevel.HAPPY
                    ),
                    HappinessEntry(
                        date = LocalDate.parse("2024-06-03"),
                        happinessLevel = HappinessLevel.NEUTRAL
                    ),
                    HappinessEntry(
                        date = LocalDate.parse("2024-06-04"),
                        happinessLevel = HappinessLevel.UNHAPPY
                    ),
                    HappinessEntry(
                        date = LocalDate.parse("2024-06-05"),
                        happinessLevel = HappinessLevel.VERY_UNHAPPY
                    ),
                )
            )
        )
    }
}

@Preview
@Composable
private fun HistoryScreenPreview_Empty() {
    MaterialTheme {
        HistoryScreen(
            HistoryUiState(
                historyItems = emptyList()
            )
        )
    }
}

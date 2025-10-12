package io.github.ilikeyourhat.happinesstracker.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.title
import io.github.ilikeyourhat.happinesstracker.di.AppGraph
import io.github.ilikeyourhat.happinesstracker.domain.HappinessLevel
import io.github.ilikeyourhat.happinesstracker.ui.HappinessScale
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(appGraph: AppGraph) {
    val viewModel = viewModel { appGraph.homeViewModel }
    val state by viewModel.uiState.collectAsState()
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.onResume()
    }
    HomeScreen(state, viewModel::onHappinessLevelClicked)
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onHappinessLevelClicked: (HappinessLevel) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
            selectedLevel = uiState.selectedHappinessLevel,
            onHappinessSelected = { level ->
                onHappinessLevelClicked(level)
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview_Selected() {
    MaterialTheme {
        HomeScreen(
            uiState = HomeUiState(
                selectedHappinessLevel = HappinessLevel.HAPPY,
            ),
            onHappinessLevelClicked = {}
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview_Unselected() {
    MaterialTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onHappinessLevelClicked = {}
        )
    }
}

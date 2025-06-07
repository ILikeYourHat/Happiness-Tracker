package io.github.ilikeyourhat.happinesstracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVeryDissatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
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
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationBar(
                listOf(
                    NavigationItem(
                        route = "home",
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unSelectedIcon = Icons.Outlined.Home
                    ),
                    NavigationItem(
                        route = "stats",
                        title = "Stats",
                        selectedIcon = Icons.Filled.PieChart,
                        unSelectedIcon = Icons.Outlined.PieChart
                    ),
                    NavigationItem(
                        route = "history",
                        title = "History",
                        selectedIcon = Icons.Filled.CalendarMonth,
                        unSelectedIcon = Icons.Outlined.CalendarMonth
                    ),
                    NavigationItem(
                        route = "settings",
                        title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unSelectedIcon = Icons.Outlined.Settings
                    )
                ),
                currentRoute = "home",
                onItemClick = { navigationItem ->
                    // Handle navigation item click
                    println("Clicked on: ${navigationItem.title}")
                }
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

@Composable
fun BottomNavigationBar(
    items: List<NavigationItem>,
    currentRoute: String?,
    onItemClick: (NavigationItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items.forEach { navigationItem ->
            NavigationBarItem(
                selected = currentRoute == navigationItem.route,
                onClick = { onItemClick(navigationItem) },
                icon = {
                    Icon(
                        imageVector = if (navigationItem.route == currentRoute) navigationItem.selectedIcon else navigationItem.unSelectedIcon,
                        contentDescription = navigationItem.title,
                    )
                },
                label = {
                    Text(
                        text = navigationItem.title,
                        style = if (navigationItem.route == currentRoute) MaterialTheme.typography.labelLarge
                        else MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }
    }
}

data class NavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)

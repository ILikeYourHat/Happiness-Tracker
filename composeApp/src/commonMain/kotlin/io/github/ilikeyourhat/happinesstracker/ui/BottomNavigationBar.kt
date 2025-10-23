package io.github.ilikeyourhat.happinesstracker.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import io.github.ilikeyourhat.happinesstracker.navigation.Screen

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onItemClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Screen.Home,
        Screen.Stats,
        Screen.History,
        Screen.Settings
    )
    val isCurrentScreen: (Screen) -> Boolean = { it.route == currentRoute }

    NavigationBar(
        modifier = modifier.fillMaxWidth()
            .safeContentPadding(),
    ) {
        items.forEach { navigationItem ->
            NavigationBarItem(
                selected = isCurrentScreen(navigationItem),
                onClick = { onItemClick(navigationItem) },
                icon = {
                    Icon(
                        imageVector = if (isCurrentScreen(navigationItem)) {
                            navigationItem.selectedIcon
                        } else {
                            navigationItem.unSelectedIcon
                        },
                        contentDescription = navigationItem.title,
                    )
                },
                label = {
                    Text(
                        text = navigationItem.title,
                        style = if (isCurrentScreen(navigationItem)) {
                            MaterialTheme.typography.labelLarge
                        } else {
                            MaterialTheme.typography.labelMedium
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }
    }
}

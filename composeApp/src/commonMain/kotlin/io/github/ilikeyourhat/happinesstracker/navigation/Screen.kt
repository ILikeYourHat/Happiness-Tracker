package io.github.ilikeyourhat.happinesstracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String
) {

    data object Home : BottomNavigationScreen(
        route = "home",
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object Stats : BottomNavigationScreen(
        route = "stats",
        title = "Stats",
        selectedIcon = Icons.Filled.PieChart,
        unSelectedIcon = Icons.Outlined.PieChart
    )

    data object History : BottomNavigationScreen(
        route = "history",
        title = "History",
        selectedIcon = Icons.Filled.CalendarMonth,
        unSelectedIcon = Icons.Outlined.CalendarMonth
    )

    data object Settings : BottomNavigationScreen(
        route = "settings",
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings
    )
}

sealed class BottomNavigationScreen(
    route: String,
    title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) : Screen(route, title)

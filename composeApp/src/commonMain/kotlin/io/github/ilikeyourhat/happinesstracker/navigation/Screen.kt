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
import happinesstracker.composeapp.generated.resources.Res
import happinesstracker.composeapp.generated.resources.navigation_history
import happinesstracker.composeapp.generated.resources.navigation_home
import happinesstracker.composeapp.generated.resources.navigation_settings
import happinesstracker.composeapp.generated.resources.navigation_stats
import org.jetbrains.compose.resources.StringResource

sealed class Screen(
    val route: String,
    val title: StringResource
) {

    data object Home : BottomNavigationScreen(
        route = "home",
        title = Res.string.navigation_home,
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    )

    data object Stats : BottomNavigationScreen(
        route = "stats",
        title = Res.string.navigation_stats,
        selectedIcon = Icons.Filled.PieChart,
        unSelectedIcon = Icons.Outlined.PieChart
    )

    data object History : BottomNavigationScreen(
        route = "history",
        title = Res.string.navigation_history,
        selectedIcon = Icons.Filled.CalendarMonth,
        unSelectedIcon = Icons.Outlined.CalendarMonth
    )

    data object Settings : BottomNavigationScreen(
        route = "settings",
        title = Res.string.navigation_settings,
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings
    )
}

sealed class BottomNavigationScreen(
    route: String,
    title: StringResource,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) : Screen(route, title)

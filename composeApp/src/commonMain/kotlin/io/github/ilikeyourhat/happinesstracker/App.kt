package io.github.ilikeyourhat.happinesstracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.ilikeyourhat.happinesstracker.di.AppGraph
import io.github.ilikeyourhat.happinesstracker.navigation.Screen
import io.github.ilikeyourhat.happinesstracker.ui.BottomNavigationBar
import io.github.ilikeyourhat.happinesstracker.ui.history.HistoryScreen
import io.github.ilikeyourhat.happinesstracker.ui.home.HomeScreen
import io.github.ilikeyourhat.happinesstracker.ui.settings.SettingsScreen
import io.github.ilikeyourhat.happinesstracker.ui.stats.StatsScreen

@Composable
fun App(
    appGraph: AppGraph,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Screen.Home.route
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .safeContentPadding()
            ) {
                composable(route = Screen.Home.route) {
                    HomeScreen(appGraph)
                }
                composable(route = Screen.Stats.route) {
                    StatsScreen()
                }
                composable(route = Screen.History.route) {
                    HistoryScreen(appGraph)
                }
                composable(route = Screen.Settings.route) {
                    SettingsScreen()
                }
            }
            BottomNavigationBar(
                currentRoute = currentRoute,
                onItemClick = { navigationItem ->
                    navController.navigate(navigationItem.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}

package io.github.ilikeyourhat.happinesstracker.di

import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.DatabaseFactory
import io.github.ilikeyourhat.happinesstracker.ui.history.HistoryViewModel
import io.github.ilikeyourhat.happinesstracker.ui.home.HomeViewModel

interface AppGraph {
    val historyViewModel: HistoryViewModel
    val homeViewModel: HomeViewModel

    @Provides
    private fun provideAppDatabase(factory: DatabaseFactory): AppDatabase {
        return factory.create()
    }
}

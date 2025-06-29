package io.github.ilikeyourhat.happinesstracker.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.DatabaseFactory
import io.github.ilikeyourhat.happinesstracker.domain.db.HappinessDao
import io.github.ilikeyourhat.happinesstracker.ui.history.HistoryViewModel
import io.github.ilikeyourhat.happinesstracker.ui.home.HomeViewModel

interface AppGraph {
    val historyViewModel: HistoryViewModel
    val homeViewModel: HomeViewModel

    @Provides
    @SingleIn(AppScope::class)
    private fun provideAppDatabase(factory: DatabaseFactory): AppDatabase {
        return factory.create()
    }

    @Provides
    private fun provideHappinessDao(database: AppDatabase): HappinessDao {
        return database.getHappinessDao()
    }
}

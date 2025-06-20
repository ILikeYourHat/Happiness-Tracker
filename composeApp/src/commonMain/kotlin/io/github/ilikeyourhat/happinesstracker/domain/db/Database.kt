package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.github.ilikeyourhat.happinesstracker.DbConstructorContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun getRoomDatabase(): AppDatabase {
    return DbConstructorContainer.databaseBuilder
        .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
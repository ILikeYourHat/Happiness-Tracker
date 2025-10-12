package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.Dispatchers

@Inject
class DatabaseFactory(
    private val provider: DatabaseBuilderProvider
) {

    fun create(): AppDatabase {
        return provider.provideDatabaseBuilder()
            .fallbackToDestructiveMigration(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}

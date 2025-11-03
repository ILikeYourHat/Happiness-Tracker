package io.github.ilikeyourhat.happinesstracker

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.DatabaseBuilderProvider
import net.harawata.appdirs.AppDirsFactory
import java.io.File

@Inject
class DatabaseBuilderProviderImpl : DatabaseBuilderProvider {

    override fun provideDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val appDir = AppDirsFactory.getInstance()
            .getUserDataDir(APP_DIRECTORY_NAME, null, APP_AUTHOR_NAME)

        val dbFile = File(appDir, AppDatabase.DATABASE_FILE)
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
        )
    }

    private companion object {
        const val APP_DIRECTORY_NAME = "HappinessTracker"
        const val APP_AUTHOR_NAME = "ILikeYourHat"
    }
}

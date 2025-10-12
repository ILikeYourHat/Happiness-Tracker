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
            .getUserDataDir("HappinessTracker", null, "ILikeYourHat")

        val dbFile = File(appDir, AppDatabase.DATABASE_FILE)
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
        )
    }
}

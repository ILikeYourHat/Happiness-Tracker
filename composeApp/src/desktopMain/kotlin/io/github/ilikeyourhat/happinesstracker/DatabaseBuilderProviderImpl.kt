package io.github.ilikeyourhat.happinesstracker

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.DatabaseBuilderProvider
import java.io.File

@Inject
class DatabaseBuilderProviderImpl : DatabaseBuilderProvider {

    override fun provideDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "room.db")
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
        )
    }
}

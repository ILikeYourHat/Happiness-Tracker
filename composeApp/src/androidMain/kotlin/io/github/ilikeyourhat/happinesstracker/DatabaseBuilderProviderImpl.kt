package io.github.ilikeyourhat.happinesstracker

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.zacsweers.metro.Inject
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.DatabaseBuilderProvider

@Inject()
class DatabaseBuilderProviderImpl(
    private val context: Context
) : DatabaseBuilderProvider {

    override fun provideDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = context.getDatabasePath(AppDatabase.DATABASE_FILE)
        return Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
    }
}

package io.github.ilikeyourhat.happinesstracker

import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}

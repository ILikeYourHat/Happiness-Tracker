package io.github.ilikeyourhat.happinesstracker

import androidx.room.RoomDatabase
import io.github.ilikeyourhat.happinesstracker.domain.db.AppDatabase

object DbConstructorContainer {
    lateinit var databaseBuilder: RoomDatabase.Builder<AppDatabase>
}
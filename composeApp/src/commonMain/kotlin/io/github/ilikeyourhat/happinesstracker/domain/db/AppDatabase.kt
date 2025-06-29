package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [HappinessLevelEntity::class],
    version = 1
)
@TypeConverters(
    DateTimeConverters::class,
    HappinessConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHappinessDao(): HappinessDao
}

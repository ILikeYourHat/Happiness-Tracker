package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters

@Database(
    entities = [HappinessLevelEntity::class],
    version = 1
)
@TypeConverters(
    DateTimeConverters::class,
    HappinessConverters::class
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHappinessDao(): HappinessDao
}

// The Room compiler generates the `actual` implementations
// https://issuetracker.google.com/issues/342905180
@Suppress("KotlinNoActualForExpect", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

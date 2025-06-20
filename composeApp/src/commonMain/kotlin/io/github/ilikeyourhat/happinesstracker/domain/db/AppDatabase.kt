package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.ConstructedBy
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(entities = [HappinessLevelEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): HappinessLevelDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

@Dao
interface HappinessLevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: HappinessLevelEntity)

    @Query("SELECT * FROM HappinessLevelEntity")
    suspend fun getAll(): List<HappinessLevelEntity>
}

@Entity
data class HappinessLevelEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val level: String
)

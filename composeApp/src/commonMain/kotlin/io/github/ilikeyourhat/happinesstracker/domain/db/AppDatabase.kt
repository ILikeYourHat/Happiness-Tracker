package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [HappinessLevelEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): HappinessLevelDao
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

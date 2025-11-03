package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface HappinessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: HappinessLevelEntity)

    @Query("SELECT * FROM HappinessLevelEntity WHERE date = :date")
    suspend fun getByDate(date: LocalDate): HappinessLevelEntity?

    @Query("SELECT * FROM HappinessLevelEntity")
    fun observe(): Flow<List<HappinessLevelEntity>>

    @Query("DELETE FROM HappinessLevelEntity WHERE date = :date")
    suspend fun clearByDate(date: LocalDate)
}

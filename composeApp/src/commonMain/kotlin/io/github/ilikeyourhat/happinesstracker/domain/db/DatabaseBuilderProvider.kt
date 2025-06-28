package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.RoomDatabase

interface DatabaseBuilderProvider {
    fun provideDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
}

package io.github.ilikeyourhat.happinesstracker.domain.db

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class DateTimeConverters {

    @TypeConverter
    fun stringToDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun dateToString(date: LocalDate?): String? {
        return date?.toString()
    }
}

package com.javadsh98.mjpersiondictionary.data.db.convertor

import androidx.room.TypeConverter
import java.util.*

class Converter {


    @TypeConverter
    fun toDate(time: Long) : Date
            = Date(time)

    @TypeConverter
    fun toLong(date: Date) : Long
            = date.time

}
package com.javadsh98.mjpersiondictionary.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "word_tb")
data class Word constructor(@PrimaryKey(autoGenerate = true) var id: Int = 0
                            , @ColumnInfo(name = "persian_word") var persianWord: String = ""
                            , @ColumnInfo(name = "english_word") var englishWord: String = ""
                            , @ColumnInfo(name = "favorite") var favorite: Boolean = false
                            , @ColumnInfo(name = "view_count") var viewCount: Int = 0
                            , @ColumnInfo(name = "fav_date") var date: Date? = Date(0)): Parcelable

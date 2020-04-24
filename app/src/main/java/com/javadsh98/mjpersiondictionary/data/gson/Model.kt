package com.javadsh98.mjpersiondictionary.data.gson

import com.google.gson.annotations.SerializedName

data class Word(@SerializedName("EnglishWord") var englisgh: String
                 , @SerializedName("PersianWord") var persian: String)

data class Dictionary(@SerializedName("dirctionary")var list: List<Word>)


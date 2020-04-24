package com.javadsh98.mjpersiondictionary.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.javadsh98.mjpersiondictionary.data.db.WordDB
import com.javadsh98.mjpersiondictionary.data.db.entity.Word

import kotlinx.coroutines.CoroutineScope

class Repository private constructor(var application: Application, var scope: CoroutineScope){

    lateinit var database: WordDB

    init {
        database = WordDB.getInstance(application, scope)
    }

    companion object{

        var INSTANCE : Repository? = null

        fun getInstance(application: Application, scope: CoroutineScope): Repository{
            if (INSTANCE == null)
                INSTANCE = Repository(application, scope)
            return INSTANCE as Repository
        }

    }

//    suspend fun getAllWords(): LiveData<List<Word>>{
//        return database.getWordDao().readAllEnglishWord()
//    }

    suspend fun searchEnglish(english: String) : LiveData<List<Word>>{
        return database.getWordDao().readEnglishWord(english)
    }

    suspend fun searchPersian(persian: String) : LiveData<List<Word>>{
        return database.getWordDao().readPersianWord(persian)
    }

    suspend fun update(word: Word){
        database.getWordDao().update(word)
    }

    suspend fun searchFavorites(): LiveData<List<Word>>{
        return database.getWordDao().readAllFavorite()
    }

    suspend fun getHistories(): LiveData<List<Word>>{
        return database.getWordDao().readHistories()
    }

    suspend fun clearHistories(){
        database.getWordDao().clearHistories()
    }

    fun getDefaultWord(number: Int) : LiveData<List<Word>>{
        return database.getWordDao().getDefaultWord(number)
    }

}
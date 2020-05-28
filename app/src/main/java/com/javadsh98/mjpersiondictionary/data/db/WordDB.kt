package com.javadsh98.mjpersiondictionary.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.javadsh98.mjpersiondictionary.data.db.convertor.Converter
import com.javadsh98.mjpersiondictionary.data.db.dao.WordDao
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Word::class) , version = 2, exportSchema = true )
@TypeConverters(value = arrayOf(Converter::class))
abstract class WordDB : RoomDatabase() {

    abstract fun getWordDao(): WordDao

    companion object{
        val TAG = WordDB::class.java.simpleName

        var migration: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
        var INSTANCE : WordDB? = null

        fun getInstance(application: Application, scope: CoroutineScope): WordDB{
            if(INSTANCE == null)
                INSTANCE = Room.databaseBuilder(application, WordDB::class.java, "w_database")
//                    .addCallback(WordCallBack(scope, application))
                    .addMigrations(migration)
                    .createFromAsset("database/word_db")
                    .build()
            return INSTANCE as WordDB
        }

    }

    private class WordCallBack(val scope: CoroutineScope, val application: Application) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

//            INSTANCE.let {

//                scope.launch {
//                    it?.getWordDao()?.deleteAllWords()
//
//                    var json: String? = null
//                    try {
//                        val inputStream: InputStream = application.assets.open("database/data.json")
//                        val size: Int = inputStream.available()
//                        val buffer = ByteArray(size)
//                        inputStream.read(buffer)
//                        inputStream.close()
//                        json = String(buffer, Charsets.UTF_8)
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//
//                    var gson = Gson()
//                    var word = gson.fromJson(json, Dictionary::class.java)
//
//                    for (i in 0 until word.list.size){
//                        var w = word.list[i]
//                        var word = Word(persianWord = w.persian, englishWord = w.englisgh)
//                        Log.d(TAG, "english word = ${word.englishWord}")
//                        it?.getWordDao()?.insert(word)
//                    }
//
//                }
//
//
//            }

        }
    }

}
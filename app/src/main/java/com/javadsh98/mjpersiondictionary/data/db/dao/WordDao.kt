package com.javadsh98.mjpersiondictionary.data.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.javadsh98.mjpersiondictionary.data.db.entity.Word

@Dao
interface WordDao{

    @Insert
    suspend fun insert(word: Word)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("delete from word_tb")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM word_tb WHERE :persian like english_word")
    fun readEnglishWord(persian: String): LiveData<List<Word>>

    @Query("SELECT * FROM word_tb WHERE :english like persian_word")
    fun readPersianWord(english: String): LiveData<List<Word>>

    @Query("SELECT * FROM word_tb WHERE favorite = 1 ORDER BY fav_date DESC")
    fun readAllFavorite(): LiveData<List<Word>>

    @Query("SELECT * FROM word_tb WHERE view_count > 0 ORDER BY view_count ASC")
    fun readHistories(): LiveData<List<Word>>

    @Query("update word_tb set view_count = 0 where view_count > 0")
    suspend fun clearHistories()

    @Query("select * from word_tb limit :number")
    fun getDefaultWord(number: Int): LiveData<List<Word>>

}
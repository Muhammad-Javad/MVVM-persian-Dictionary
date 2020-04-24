package com.javadsh98.mjpersiondictionary.ui.main.fragment.History

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.javadsh98.mjpersiondictionary.data.Repository
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    var repository : Repository
    lateinit var allWords: LiveData<List<Word>>

    init {
        repository = Repository.getInstance(application, viewModelScope)
        viewModelScope.launch {
            allWords = repository.getHistories()
        }
    }

    fun getHistory(): LiveData<List<Word>>{
        return allWords
    }

    fun update(word: Word){
        viewModelScope.launch {
            repository.update(word)
        }
    }

    fun clearHistories(){
        viewModelScope.launch {
            repository.clearHistories()
        }
    }

}
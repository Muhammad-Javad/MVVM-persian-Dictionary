package com.javadsh98.mjpersiondictionary.ui.main.fragment.home

import android.app.Application
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.arch.core.util.Function
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.javadsh98.mjpersiondictionary.data.Repository
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application){

    var repository : Repository
    var allWords: LiveData<List<Word>> = MutableLiveData(listOf())
    var searchWord = ""
    var pageNumber : Int = 1
    var countNumber: Int = 25

    init {
        repository = Repository.getInstance(application, viewModelScope)
    }

    fun searchEnglish(english: String) : LiveData<List<Word>>{
        viewModelScope.launch {
            allWords = repository.searchEnglish(english)
        }
        return Transformations.distinctUntilChanged(allWords!!)
    }

    fun searchPersian(english: String) : LiveData<List<Word>>{
        viewModelScope.launch {
            allWords = repository.searchPersian(english)
        }
        return Transformations.distinctUntilChanged(allWords!!)
    }

    fun update(word: Word){
        viewModelScope.launch {
            repository.update(word)
        }
    }

    fun getAllWord(): LiveData<List<Word>>{
        if (allWords != null)
            return Transformations.distinctUntilChanged(allWords!!)
        else
            return allWords
    }

    fun getDefaultWord(): LiveData<List<Word>>{
        viewModelScope.launch {
            allWords = repository.getDefaultWord(countNumber * pageNumber)
        }
        return Transformations.distinctUntilChanged(allWords!!)
    }

    fun loadMore(): LiveData<List<Word>>{
        ++pageNumber
        return getDefaultWord()
    }

}
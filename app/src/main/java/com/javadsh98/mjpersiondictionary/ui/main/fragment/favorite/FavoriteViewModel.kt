package com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.javadsh98.mjpersiondictionary.data.Repository
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application){

    private var repository: Repository
    private lateinit var allFavorites: LiveData<List<Word>>

    init {
        repository = Repository.getInstance(application, viewModelScope)
        viewModelScope.launch {
            allFavorites = repository.searchFavorites()
        }
    }

    fun searchFavorites(): LiveData<List<Word>>{
        return allFavorites
    }

    fun update(word: Word) {
        viewModelScope.launch {
            repository.update(word)
        }
    }

}
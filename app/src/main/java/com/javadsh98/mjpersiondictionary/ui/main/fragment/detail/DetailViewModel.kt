package com.javadsh98.mjpersiondictionary.ui.main.fragment.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javadsh98.mjpersiondictionary.data.Repository
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application){

    val repo: Repository = Repository.getInstance(application, viewModelScope)

    fun update(word: Word){
        viewModelScope.launch {
            repo.update(word)
        }
    }

}
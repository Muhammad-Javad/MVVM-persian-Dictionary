package com.javadsh98.mjpersiondictionary.ui.main.activity

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private var bottomNavigationPosition: BottomPosition = BottomPosition.HOME

    fun setPosition(position: BottomPosition) {
        this.bottomNavigationPosition = position
    }

    fun getPosition(): BottomPosition = bottomNavigationPosition

}

enum class BottomPosition {
    HOME, FAVORITE, HISTORY, MORE
}
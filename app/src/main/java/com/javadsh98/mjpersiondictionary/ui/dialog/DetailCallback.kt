package com.javadsh98.mjpersiondictionary.ui.dialog

import com.javadsh98.mjpersiondictionary.data.db.entity.Word

interface DetailCallback {
    fun onOkClicked(word: Word)
    fun onNoClicked()
}
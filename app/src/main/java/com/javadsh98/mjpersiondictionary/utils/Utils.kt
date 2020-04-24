package com.javadsh98.mjpersiondictionary.utils

import android.content.Context
import android.util.DisplayMetrics


class Utils{

    companion object{
        fun getScreenHeight(context: Context):Float{
            val displayMetrics: DisplayMetrics = context.getResources().getDisplayMetrics()
            val dpHeight = displayMetrics.heightPixels / displayMetrics.density
            return dpHeight
        }
    }

}
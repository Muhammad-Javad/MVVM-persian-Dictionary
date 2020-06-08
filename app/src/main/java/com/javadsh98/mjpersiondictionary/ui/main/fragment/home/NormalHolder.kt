package com.javadsh98.mjpersiondictionary.ui.main.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.android.synthetic.main.item_normal.view.*
import java.util.*

class NormalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(viewGroup: ViewGroup) : NormalHolder{
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_normal, viewGroup, false)
            return NormalHolder(view)
        }
    }

    fun bind(word: Word, itemClick: onItemClick){
        itemView.textview_allrecycler_persian.text = word.persianWord
        itemView.textview_allrecycler_english.text = word.englishWord


        cardviewClickListener(word, itemClick)

    }

    private fun cardviewClickListener(word: Word, itemClick: onItemClick) {
        itemView.cardview_allrecycler_item.setOnClickListener{
            itemClick.invoke(word)
        }
    }

}
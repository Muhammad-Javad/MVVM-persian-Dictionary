package com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.onItemClick
import kotlinx.android.synthetic.main.item_normal.view.*
import java.util.*

class FavoriteHolder(var itemview: View): RecyclerView.ViewHolder(itemview){

    companion object{

        fun create(viewGroup: ViewGroup) : FavoriteHolder{
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_normal, viewGroup, false)
            return FavoriteHolder(view)
        }

    }

    fun bind(word: Word, onItemListener: onItemClick, likeListener: onItemClick) {
        itemview.textview_allrecycler_persian.text = word.persianWord
        itemview.textview_allrecycler_english.text = word.englishWord
        itemview.textview_allrecycler_italian.text = word.italianWord

        itemview.setOnClickListener { onItemListener(word) }

        //like :
//        likeState(word)
//        likeOnClickListener(word, likeListener)

    }

//    private fun likeOnClickListener(word: Word, likeListener: onItemClick) {
//        itemview.imageview_detail_like.setOnClickListener {
//            word.favorite = !word.favorite
//            word.date = Date(System.currentTimeMillis())
//            likeState(word)
//            likeListener.invoke(word)
//        }
//    }
//
//    private fun likeState(word: Word) {
//        if (word.favorite)
//            itemview.imageview_detail_like.setImageResource(R.drawable.ic_all_like_24)
//        else
//            itemview.imageview_detail_like.setImageResource(R.drawable.ic_all_dislike_24)
//    }

}

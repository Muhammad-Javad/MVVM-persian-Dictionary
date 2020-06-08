package com.javadsh98.mjpersiondictionary.ui.main.fragment.History

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.HomeAdapter
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.onItemClick
import kotlinx.android.synthetic.main.item_normal.view.*
import java.util.*

class HistoryAdapter(): ListAdapter<Word, RecyclerView.ViewHolder>(HomeAdapter.diff){

    lateinit var likeListener: onItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        return HistoryHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HistoryHolder){
            holder.bind(getItem(position))
        }
    }

    class HistoryHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        companion object{

            fun create(parent: ViewGroup): HistoryHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_normal, parent, false)
                return HistoryHolder(view)
            }

        }

        fun bind(word: Word) {
            itemView.textview_allrecycler_persian.text = word.persianWord
            itemView.textview_allrecycler_english.text = word.englishWord


            //like :
//            likeState(word)
//            likeOnClickListener(word, likeListener)

        }
//
//        private fun likeOnClickListener(word: Word, likeListener : onItemClick) {
//            itemView.imageview_detail_like.setOnClickListener {
//                word.favorite = !word.favorite
//                word.date = Date(System.currentTimeMillis())
//                likeState(word)
//                likeListener.invoke(word)
//            }
//        }
//
//        private fun likeState(word: Word) {
//            if (word.favorite)
//                itemView.imageview_detail_like.setImageResource(R.drawable.ic_all_like_24)
//            else
//                itemView.imageview_detail_like.setImageResource(R.drawable.ic_all_dislike_24)
//        }

    }


}

package com.javadsh98.mjpersiondictionary.ui.main.fragment.home

import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.mjpersiondictionary.data.db.entity.Word

typealias onItemClick = (Word) -> Unit

class HomeAdapter ()
    : ListAdapter<Word, NormalHolder>(diff){

    lateinit var itemListener: onItemClick

    private val NORMAL = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalHolder {
            return NormalHolder.create(parent)
    }

    override fun onBindViewHolder(holder:NormalHolder, position: Int) {
            holder.bind(getItem(position), itemListener)

    }

    companion object{

        val diff = object: DiffUtil.ItemCallback<Word>(){
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean
                = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean
                = TextUtils.equals(oldItem.persianWord, newItem.persianWord)
                    && TextUtils.equals(oldItem.englishWord, newItem.englishWord)

        }
    }
}

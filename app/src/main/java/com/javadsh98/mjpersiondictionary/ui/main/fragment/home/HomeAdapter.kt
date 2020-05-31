package com.javadsh98.mjpersiondictionary.ui.main.fragment.home

import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.mjpersiondictionary.data.db.entity.Word

typealias onItemClick = (Word) -> Unit
typealias onMoreClick = () -> Unit

class HomeAdapter ()
    : ListAdapter<Word, RecyclerView.ViewHolder>(diff){

    lateinit var itemListener: onItemClick
    lateinit var likeListener: onItemClick
    lateinit var moreListener: onMoreClick

    private val NORMAL = 1
    private val MORE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == NORMAL)
            return NormalHolder.create(parent)
        else
            return MoreHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is NormalHolder){
            holder.bind(getItem(position), itemListener, likeListener)
        }else if(holder is MoreHolder){
            holder.bind(moreListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount())
            NORMAL
        else
            MORE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
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

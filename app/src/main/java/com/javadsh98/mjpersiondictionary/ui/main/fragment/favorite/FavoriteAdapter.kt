package com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite

import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.HomeAdapter
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.onItemClick

class FavoriteAdapter()
    : ListAdapter<Word, RecyclerView.ViewHolder>(HomeAdapter.diff){

    lateinit var likeListener: onItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoriteHolder)
            holder.bind(getItem(position), likeListener)
    }


}

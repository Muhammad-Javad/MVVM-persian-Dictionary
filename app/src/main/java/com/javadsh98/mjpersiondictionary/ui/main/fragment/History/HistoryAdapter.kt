package com.javadsh98.mjpersiondictionary.ui.main.fragment.History

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import com.javadsh98.mjpersiondictionary.databinding.ItemAllBinding
import kotlinx.android.synthetic.main.item_all.view.*
import java.util.*

class HistoryAdapter (val callback: OnItemClickCallBack): RecyclerView.Adapter<HistoryAdapter.HistoryHolder>(){

    var words: List<Word> = listOf()

    fun setList(words: List<Word>){

        if(this@HistoryAdapter.words.isEmpty()){
            this@HistoryAdapter.words = words
            notifyDataSetChanged()
        }else{

            var result: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    var newWord = words[newItemPosition]
                    var oldWord = this@HistoryAdapter.words[oldItemPosition]
                    return newWord.id == oldWord.id
                }
                override fun getOldListSize(): Int {
                    return this@HistoryAdapter.words.size
                }
                override fun getNewListSize(): Int {
                    return words.size
                }
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    var newWord = words[newItemPosition]
                    var oldWord = this@HistoryAdapter.words[oldItemPosition]
                    return newWord.id == oldWord.id
                            && newWord.favorite == oldWord.favorite
                            && TextUtils.equals(newWord.persianWord, oldWord.persianWord)
                            && TextUtils.equals(newWord.englishWord, oldWord.englishWord)
                }

                override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                    return super.getChangePayload(oldItemPosition, newItemPosition)
                }
            })

            this@HistoryAdapter.words = words
            result.dispatchUpdatesTo(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        var root = ItemAllBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return HistoryHolder(root, callback)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.onBind(words[position])
    }


    class HistoryHolder(var itemview: View, val callback: OnItemClickCallBack): RecyclerView.ViewHolder(itemview){

        fun onBind(word: Word) {
            itemview.textview_allrecycler_persian.text = word.persianWord
            itemview.textview_allrecycler_english.text = word.englishWord

            //like :
            likeState(word)
            likeOnClickListener(word)

            //more
            moreVisibility()
        }

        private fun likeOnClickListener(word: Word) {
            itemview.imageview_allrecycler_like.setOnClickListener {
                word.favorite = !word.favorite
                word.date = Date(System.currentTimeMillis())
                likeState(word)
                callback.onLikeClick(word)
            }
        }

        private fun likeState(word: Word) {
            if (word.favorite)
                itemview.imageview_allrecycler_like.setImageResource(R.drawable.ic_all_like_24)
            else
                itemview.imageview_allrecycler_like.setImageResource(R.drawable.ic_all_dislike_24)
        }

        private fun moreVisibility() {
            itemview.button_allrecycler_load_more.visibility = View.GONE
        }

    }
}

interface OnItemClickCallBack{

    fun onLikeClick(word: Word)

}
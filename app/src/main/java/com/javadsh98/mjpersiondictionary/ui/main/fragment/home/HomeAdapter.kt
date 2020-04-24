package com.javadsh98.mjpersiondictionary.ui.main.fragment.home

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

class HomeAdapter (val callback: OnItemClickCallBack): RecyclerView.Adapter<HomeAdapter.HomeHolder>(){

    var words: List<Word> = listOf()

    fun setList(words: List<Word>){

        if(this@HomeAdapter.words.isEmpty()){
            this@HomeAdapter.words = words
            notifyDataSetChanged()
        }else{

            var result: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    var newWord = words[newItemPosition]
                    var oldWord = this@HomeAdapter.words[oldItemPosition]
                    return newWord.id == oldWord.id
                }
                override fun getOldListSize(): Int {
                    return this@HomeAdapter.words.size
                }
                override fun getNewListSize(): Int {
                    return words.size
                }
                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    var newWord = words[newItemPosition]
                    var oldWord = this@HomeAdapter.words[oldItemPosition]
                    return newWord.id == oldWord.id
                            && newWord.favorite == oldWord.favorite
                            && TextUtils.equals(newWord.persianWord, oldWord.persianWord)
                            && TextUtils.equals(newWord.englishWord, oldWord.englishWord)
                }

                override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                    return super.getChangePayload(oldItemPosition, newItemPosition)
                }
            })

            this@HomeAdapter.words = words
            result.dispatchUpdatesTo(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        var root = ItemAllBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return HomeHolder(root, callback)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.onBind(words[position], position, words.size - 1)
    }


    class HomeHolder(var itemview: View, val callback: OnItemClickCallBack): RecyclerView.ViewHolder(itemview){

        fun onBind(word: Word,currentItem: Int, lastItem: Int) {
            itemview.textview_allrecycler_persian.text = word.persianWord
            itemview.textview_allrecycler_english.text = word.englishWord

            //like :
            likeState(word)
            likeOnClickListener(word)

            //more
            moreVisibility(currentItem, lastItem)
            moreOnClickListener()

            //cardview
            cardviewClickListener(word)
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

        private fun cardviewClickListener(word: Word) {
            itemview.cardview_allrecycler_item.setOnClickListener{
                callback.onItemClick(word)
            }
        }

        private fun moreOnClickListener() {
            itemview.button_allrecycler_load_more.setOnClickListener {
                hideMore()
                callback.onMoreClick()
            }
        }

        private fun moreVisibility(currentItem: Int, lastItem: Int) {

            if (currentItem == lastItem) {
                if(lastItem > 23)
                    showMore()
                else
                    hideMore()
            }else {
                hideMore()
            }
        }

        private fun hideMore(){
            itemview.button_allrecycler_load_more.visibility = View.GONE
        }

        private fun showMore(){
            itemview.button_allrecycler_load_more.visibility = View.VISIBLE
        }

    }
}

interface OnItemClickCallBack{

    fun onLikeClick(word: Word)
    fun onItemClick(word: Word)
    fun onMoreClick()

}
package com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment(): Fragment(R.layout.fragment_favorite), OnItemClickCallBack {

    lateinit var viewmodel: FavoriteViewModel
    lateinit var adapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(requireActivity()).get(FavoriteViewModel::class.java)

        setupRecycler()
        searchFavorites()
    }

    private fun searchFavorites() {

        viewmodel.searchFavorites().observe(viewLifecycleOwner, Observer {

            adapter.setList(it)
            if (it.isEmpty()) {
                showMessage()
            } else {
                hideMessage()
            }

        })
    }

    private fun hideMessage() {
        textview_favorite_message.visibility = View.GONE
    }

    private fun showMessage() {
        textview_favorite_message.visibility = View.VISIBLE
    }

    private fun setupRecycler() {
        adapter = FavoriteAdapter(this)
        recyclerview_favorite_favorites.setHasFixedSize(true)
        recyclerview_favorite_favorites.adapter = adapter
    }

    override fun onLikeClick(word: Word) {
        viewmodel.update(word)
    }

}

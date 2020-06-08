package com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment(): Fragment(R.layout.fragment_favorite) {

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

            adapter.submitList(it)
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
        adapter = FavoriteAdapter()
        adapter.likeListener = {
            viewmodel.update(it)
        }
        adapter.onItemClick = {
            //update view count
            ++it.viewCount
            viewmodel.update(word = it)
            //goto detail fragment
            val action = FavoriteFragmentDirections.actionFavoriteFragment2ToDetailFragment3(it, it.englishWord)
            findNavController().navigate(action)
        }

        recyclerview_favorite_favorites.setHasFixedSize(true)
        recyclerview_favorite_favorites.adapter = adapter
    }

}

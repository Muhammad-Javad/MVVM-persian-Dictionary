package com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import com.javadsh98.mjpersiondictionary.databinding.FragmentFavoriteBinding
import com.javadsh98.mjpersiondictionary.ui.main.activity.MainActivity
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.HomeAdapter
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.HomeFragment
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class FavoriteFragment(): Fragment(), OnItemClickCallBack {


    var binding: FragmentFavoriteBinding? = null
    lateinit var viewmodel: FavoriteViewModel
    lateinit var root: View
    lateinit var adapter: FavoriteAdapter

    companion object{

        var instance: FavoriteFragment? = null

        fun newInstance(): FavoriteFragment {
            if(instance == null){
                instance = FavoriteFragment()
            }
            return instance!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        root = binding!!.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewmodel = ViewModelProvider(activity as MainActivity).get(FavoriteViewModel::class.java)

        setupRecycler()
        searchFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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
        root.recyclerview_favorite_favorites.setHasFixedSize(true)
        root.recyclerview_favorite_favorites.adapter = adapter
    }

    override fun onLikeClick(word: Word) {
        viewmodel.update(word)
    }

}

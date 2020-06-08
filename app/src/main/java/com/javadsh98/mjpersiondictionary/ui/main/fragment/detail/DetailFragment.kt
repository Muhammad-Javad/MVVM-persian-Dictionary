package com.javadsh98.mjpersiondictionary.ui.main.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {

    val args: DetailFragmentArgs by navArgs()
    lateinit var word: Word
    lateinit var viewModel : DetailViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        word = args.word
        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

        setupComponents()
    }

    private fun setupComponents() {

        //edittext
        edittext_detail_persian.setText(word.persianWord, TextView.BufferType.EDITABLE)
        edittext_detail_english.setText(word.englishWord, TextView.BufferType.EDITABLE)
        edittext_detail_italian.setText(word.englishWord, TextView.BufferType.EDITABLE)

        //like
        word.favorite.also {
            setLikeDrawable(it)
        }

        setupListener()

    }

    private fun setupListener() {
        imageview_detail_like.setOnClickListener {
            word.favorite = !word.favorite
            setLikeDrawable(word.favorite)
        }

        button_detail_submit.setOnClickListener{
            word = word.apply {
                englishWord = edittext_detail_english.text.toString()
                persianWord = edittext_detail_persian.text.toString()
                date = Date(System.currentTimeMillis())
            }
            viewModel.update(word)
//            findNavController().popBackStack(R.id.homeFragment, false)
            requireActivity().onBackPressed()
        }
    }
    fun setLikeDrawable(like: Boolean){
        if (like)
            imageview_detail_like.setImageResource(R.drawable.ic_all_like_24)
        else
            imageview_detail_like.setImageResource(R.drawable.ic_all_dislike_24)
    }
}
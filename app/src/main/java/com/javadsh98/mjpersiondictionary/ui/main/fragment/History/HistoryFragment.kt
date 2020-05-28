package com.javadsh98.mjpersiondictionary.ui.main.fragment.History

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment(): Fragment(R.layout.fragment_history), OnItemClickCallBack {

    //ui
    lateinit var adapter: HistoryAdapter

    //viewmodel
    lateinit var viewModel: HistoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)

        setupViewmodel()
        setupRecyclerview()
        setupClearHistory()
    }

    private fun setupClearHistory() {
        button_history_clear_all.setOnClickListener {
            viewModel.clearHistories()
        }
    }

    private fun setupViewmodel() {

        viewModel.getHistory().observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
            if (it.isEmpty()){
                showMessage()
            }else{
                hideMessage()
            }
        })
    }

    private fun setupRecyclerview() {
        var recyclerview = recyclerview_history_words
        adapter = HistoryAdapter(this)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = adapter
    }

    override fun onLikeClick(word: Word) {
        viewModel.update(word)
    }

    private fun showMessage(){
        textview_history_message.visibility = View.VISIBLE
    }

    private fun hideMessage(){
        textview_history_message.visibility = View.GONE
    }

}

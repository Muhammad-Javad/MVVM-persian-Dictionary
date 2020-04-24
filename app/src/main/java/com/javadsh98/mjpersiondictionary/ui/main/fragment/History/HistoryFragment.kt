package com.javadsh98.mjpersiondictionary.ui.main.fragment.History

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.Repository
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import com.javadsh98.mjpersiondictionary.databinding.FragmentHistoryBinding
import kotlinx.android.synthetic.main.fragment_history.view.*

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment(): Fragment(), OnItemClickCallBack {

    var binding: FragmentHistoryBinding? = null
    lateinit var root: View

    //ui
    lateinit var adapter: HistoryAdapter

    //viewmodel
    lateinit var viewModel: HistoryViewModel

    companion object{

        var instance: HistoryFragment? = null

        fun newInstance(): HistoryFragment{
            if(instance == null){
                instance = HistoryFragment()
            }
            return instance!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        root = binding!!.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewmodel()
        setupRecyclerview()
        setupClearHistory()
    }

    private fun setupClearHistory() {
        root.button_history_clear_all.setOnClickListener {
            viewModel.clearHistories()
        }
    }

    private fun setupViewmodel() {
        viewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)

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
        var recyclerview = root.recyclerview_history_words
        adapter = HistoryAdapter(this)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onLikeClick(word: Word) {
        viewModel.update(word)
    }

    private fun showMessage(){
        root.textview_history_message.visibility = View.VISIBLE
    }

    private fun hideMessage(){
        root.textview_history_message.visibility = View.GONE
    }

}

package com.javadsh98.mjpersiondictionary.ui.main.fragment.home

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.javadsh98.mjpersiondictionary.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment
    : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    val RTL_CHARACTERS: Pattern =
        Pattern.compile("[\u0600-\u06FF\u0750-\u077F\u0590-\u05FF\uFE70-\uFEFF]")

    lateinit var viewmodel: HomeViewModel
    var adapter: HomeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        setupSearchView()
        setupRecycler()
        setupListener()
        getPreviousData()
    }

    private fun setupSearchView() {
        searchview_home_search.setQuery(viewmodel.searchWord, false)
    }

    fun getPreviousData() {

        viewmodel.getAllWord().let {
            if(it != null)
                it.observe(viewLifecycleOwner, Observer {
                    adapter!!.submitList(it)

                })
        }

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(TextUtils.isEmpty(newText))
            getDefaultWord()

        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        setSearchword(query!!)
        if(isPersian(query))
            searchPersian(query)
        else
            searchEnglish(query)
        searchview_home_search.clearFocus()

        return false
    }

    private fun searchPersian(query: String?) {

        viewmodel.searchPersian(query!!).observe(viewLifecycleOwner, Observer {
            if(isEqualeSearchWord())
            {
                adapter!!.submitList(it)
            }
        })
    }

    private fun searchEnglish(query: String?) {

        viewmodel.searchEnglish(query!!).observe(viewLifecycleOwner, Observer {
            if (isEqualeSearchWord())
            {
                adapter!!.submitList(it)
            }
        })
    }

    private fun setupRecycler() {

        if(adapter == null)
            adapter = HomeAdapter()

        adapter!!.itemListener = {
            //insert to history
            ++it.viewCount
            viewmodel.update(it)
            //goto detail
            val action = HomeFragmentDirections.actionHomeFragment2ToDetailFragment2(it, it.englishWord)
            findNavController().navigate(action)
        }

        recyclerview_home_words.setHasFixedSize(true)
        recyclerview_home_words.adapter = adapter

        var list = viewmodel.allWords.value
        if (!list!!.isEmpty()) {
            adapter!!.submitList(list)
        } else {
            getDefaultWord()
        }

    }

    private fun setupListener() {
        searchview_home_search.setOnQueryTextListener(this)
    }

    fun isPersian(str: String): Boolean{
        val matcher: Matcher = RTL_CHARACTERS.matcher(str)
        return if (matcher.find())  true else  false
    }

    fun setSearchword(searchWord: String){
        viewmodel.searchWord = searchWord
    }

    fun getSearchWord(): String = viewmodel.searchWord

    fun isEqualeSearchWord(): Boolean{
        return TextUtils.equals(getSearchWord(), searchview_home_search.query)
    }

    fun getDefaultWord() {
        viewmodel.getDefaultWord().observe(viewLifecycleOwner, Observer {
            adapter!!.submitList(it)
        })
    }
}

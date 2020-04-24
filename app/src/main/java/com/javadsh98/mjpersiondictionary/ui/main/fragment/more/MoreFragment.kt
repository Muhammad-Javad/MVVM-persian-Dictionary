package com.javadsh98.mjpersiondictionary.ui.main.fragment.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.databinding.FragmentMoreBinding

/**
 * A simple [Fragment] subclass.
 */
class MoreFragment private constructor(): Fragment() {

    private var binding: FragmentMoreBinding? = null

    companion object{

        var instance: MoreFragment? = null

        fun newInstance(): MoreFragment{
            if(instance == null){
                instance = MoreFragment()
            }
            return instance!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding!!.textviewMoreSourceCode.setOnClickListener {

        }

        binding!!.textviewMoreShareSourceCode.setOnClickListener {

        }

        binding!!.textviewMoreExit.setOnClickListener {
            requireActivity().finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

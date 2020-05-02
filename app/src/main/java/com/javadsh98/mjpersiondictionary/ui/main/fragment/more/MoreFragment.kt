package com.javadsh98.mjpersiondictionary.ui.main.fragment.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat

import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.databinding.FragmentMoreBinding
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class MoreFragment: Fragment() {

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
            openViaCustomTab()
        }

        binding!!.textviewMoreShareSourceCode.setOnClickListener {
            ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plain")
                .setChooserTitle(R.string.text_share_via)
                .setText("https://github.com/Muhammad-Javad/MVVM-persion-Dictionary")
                .startChooser()
        }

        binding!!.textviewMoreExit.setOnClickListener {
            requireActivity().finish()
        }

    }

    fun openViaIntent(){
        var intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("https://github.com/Muhammad-Javad/MVVM-persion-Dictionary"))
        if(intent.resolveActivity(context!!.packageManager) != null) {
            startActivity(intent)
        }else{
            Toast.makeText(context, R.string.text_no_internet_explorer, Toast.LENGTH_SHORT).show()
        }
    }

    fun openViaCustomTab(){
        var customTab = CustomTabsIntent.Builder()
            .setToolbarColor(resources.getColor(R.color.colorPrimary))
            .setShowTitle(true)
            .build()
        try {
            customTab.launchUrl(context!!, Uri.parse("https://github.com/Muhammad-Javad/MVVM-persion-Dictionary"))
        }catch (e: Exception){
            openViaIntent()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

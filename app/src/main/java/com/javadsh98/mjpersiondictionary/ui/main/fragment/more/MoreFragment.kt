package com.javadsh98.mjpersiondictionary.ui.main.fragment.more

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import com.javadsh98.mjpersiondictionary.R
import kotlinx.android.synthetic.main.fragment_more.*

/**
 * A simple [Fragment] subclass.
 */
class MoreFragment: Fragment(R.layout.fragment_more) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textview_more_source_code.setOnClickListener {
            openViaCustomTab()
        }

        textview_more_share_source_code.setOnClickListener {
            ShareCompat.IntentBuilder.from(requireActivity())
                .setType("text/plain")
                .setChooserTitle(R.string.text_share_via)
                .setText("https://github.com/Muhammad-Javad/MVVM-persion-Dictionary")
                .startChooser()
        }

        textview_more_exit.setOnClickListener {
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

}

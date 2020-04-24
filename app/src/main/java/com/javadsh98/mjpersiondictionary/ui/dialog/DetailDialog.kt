package com.javadsh98.mjpersiondictionary.ui.dialog

import android.app.Dialog
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.data.db.entity.Word
import com.javadsh98.mjpersiondictionary.databinding.DialogDetailBinding
import com.javadsh98.mjpersiondictionary.utils.Utils
import kotlinx.android.synthetic.main.dialog_detail.view.*

class DetailDialog() :
    AppCompatDialogFragment() {

    var word: Word? = null
    var englishQuery: Boolean = false
    var words: List<Word>? = null

    lateinit var binding: DialogDetailBinding
    lateinit var root: View

    private lateinit var callback: DetailCallback

    companion object{

        var instance: DetailDialog? = null
        fun newInstance(word: Word, englishQuery: Boolean, words: List<Word>): DetailDialog{
            if (instance == null){
                instance = DetailDialog()

            }
            instance!!.word = word
            instance!!.englishQuery = englishQuery
            instance!!.words = words
            return instance!!
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(context!!)
        var binding = DialogDetailBinding.inflate(LayoutInflater.from(context!!))
        root = binding.root
        setRootHeight()
        builder.setView(root)

        setupUi()
        setupListener()
        var dialog = builder.create()

        setCorner(dialog)
        setMargin(dialog)

        return dialog
    }

    private fun setRootHeight() {

        var currentOrientation = resources.configuration.orientation
        if(currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
//            Toast.makeText(context!!, "landscape", Toast.LENGTH_SHORT).show()
            var height = Utils.getScreenHeight(context!!) / 1.5
            var param = root.textview_detail_meanings.layoutParams
            param.height = height.toInt()
            root.textview_detail_meanings.layoutParams = param

        }else{
//            Toast.makeText(context!!, "Portrat", Toast.LENGTH_SHORT).show()
            var height = Utils.getScreenHeight(context!!) / 2
            var param = root.textview_detail_meanings.layoutParams
            param.height = height.toInt()
            root.textview_detail_meanings.layoutParams = param
        }

    }

    private fun setMargin(dialog: AlertDialog) {
        val drawable = ColorDrawable(ContextCompat.getColor(context!!, R.color.transparent))
        val insetDrawable = InsetDrawable(drawable, 30)
        dialog.window!!.setBackgroundDrawable(insetDrawable)
    }

    private fun setCorner(dialog: AlertDialog) {
        dialog.window!!.setBackgroundDrawableResource(R.drawable.back_all_corner)
    }

    private fun setupListener() {
        root.button_detail_ok.setOnClickListener{
            callback.onOkClicked(word!!)
        }
        root.button_detail_no.setOnClickListener{
            callback.onNoClicked()
        }

    }

    private fun setupUi() {

        var meaning = getMeaning()

        if (englishQuery) {
            setQueryText(word!!.englishWord)
            setMeaningText(word!!.persianWord)
        } else {
            setQueryText(word!!.persianWord)
            setMeaningText(word!!.englishWord)
        }

        var meanings = StringBuilder("")
        for (i in 0 until words!!.size) {
            var word = words!![i]
            if (englishQuery) {
                //persian meanings
                if (!TextUtils.equals(word.persianWord, meaning)) {
                    if (i == words!!.size - 1)
                        meanings.append("${word.persianWord}\n")
                    else
                        meanings.append("${word.persianWord} ,\n")
                }

            } else {
                //english meanings
                if (!TextUtils.equals(word.englishWord, meaning)) {
                    if (i == words!!.size - 1)
                        meanings.append("${word.englishWord}\n")
                    else
                        meanings.append("${word.englishWord} ,\n")
                }
            }
        }

        setupMeanings()
        setMeaningsText(meanings.toString())

    }

    private fun setMeaningsText(text: String){
        root.textview_detail_meanings.text =text
    }

    private fun setupMeanings() {
        root.textview_detail_meanings.movementMethod = ScrollingMovementMethod()
    }

    private fun getMeaning(): String =
        if (englishQuery) word!!.persianWord else word!!.englishWord


    private fun setQueryText(text: String) {
        root.textview_detail_search_word.text = text
    }

    private fun setMeaningText(text: String){
        root.textview_detail_meaning_word.text = text
    }

    fun setCallBack(callback: DetailCallback){
        this.callback = callback
    }



}
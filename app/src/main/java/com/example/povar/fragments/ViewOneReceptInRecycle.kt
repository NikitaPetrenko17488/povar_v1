package com.example.povar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.povar.R
import com.example.povar.activity.MainActivity
import com.example.povar.ui.STORAGE_FOR_RECYCLE_RECEPT
import com.example.povar.ui.downloadSetImage
import com.example.povar.ui.showUserNameAdnImage


class ViewOneReceptInRecycle : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_one_recept_in_recycle, container, false)
    }

    override fun onStart() {
        super.onStart()
        Avotzapolnenie()

    }

    override fun onStop() {
        super.onStop()
        showUserNameAdnImage(activity!!)
    }

    fun Avotzapolnenie()
    {

        var avtozagrNameViewRecept=
            activity!!.findViewById<TextView>(R.id.EditTextNameView)
        avtozagrNameViewRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.name)
        var avtozagrIngridientsViewRecept=
            activity!!.findViewById<TextView>(R.id.EditTextIngridientView)
        avtozagrIngridientsViewRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridients)
        var avtozagrFormulaViewRecept=
            activity!!.findViewById<TextView>(R.id.EditTextFormulaView)
        avtozagrFormulaViewRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.formula)
        var avtozagrImageUpdateRecept=
            activity!!.findViewById<ImageView>(R.id.ImageViewRecept)
        avtozagrImageUpdateRecept.downloadSetImage(STORAGE_FOR_RECYCLE_RECEPT.photo)
    }


}


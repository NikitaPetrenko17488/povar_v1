package com.example.povar.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.activity.RegistryActivity
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_view1.*


class Settings : Fragment(), CompoundButton.OnCheckedChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        switchTemnayaTema?.isChecked = STORAGE.Tema
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)
       hideSettings(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()
        switchTemnayaTema?.isChecked = STORAGE.Tema
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)
        hideSettings(activity!!)


        switchTemnayaTema.setOnCheckedChangeListener(this)

        Exit.setOnClickListener{

            startActivity(Intent(activity, RegistryActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()

    }

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCheckedChanged(Component: CompoundButton?, isChecked: Boolean) {

        //Component!!.setChecked(STORAGE.Tema)

        if(isChecked==true) {

            activity!!.LinearActivity.setBackgroundResource(R.color.DarkThema)
            activity!!.LinearActivityBottom.setBackgroundResource(R.color.DarkThema)
            activity!!.toolbar.setBackgroundResource(R.color.DarkThema)
            STORAGE.Tema=isChecked
        }
        if(isChecked==false)
        {

            activity!!.toolbar.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivity.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivityBottom.setBackgroundResource(R.color.LiteThema)
            STORAGE.Tema=isChecked

        }


    }
}



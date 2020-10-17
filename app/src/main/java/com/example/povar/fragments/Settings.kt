package com.example.povar.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
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
        hideAddButton(activity!!)

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
        hideAddButton(activity!!)

        if (STORAGE.Tema==true)
        {
            ConstraintSettings.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            Exit.setTextColor(Color.parseColor("#b2b2b2"))
            Exit.background=null
            switchTemnayaTema.setTextColor(Color.parseColor("#b2b2b2"))
        }
        else
        {
            ConstraintSettings.setBackgroundResource(R.drawable.fon_na_fragment)
            Exit.setTextColor(Color.parseColor("#000000"))
            Exit.setBackgroundResource(R.drawable.megaoval)
            switchTemnayaTema.setTextColor(Color.parseColor("#000000"))
        }

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


        if(isChecked==true) {

            activity!!.LinearActivity.setBackgroundResource(R.color.DarkThema)
            activity!!.LinearActivityBottom.setBackgroundResource(R.color.DarkThema)
            activity!!.toolbar.setBackgroundResource(R.color.DarkThema)
            STORAGE.Tema=isChecked
            activity!!.ConstraintSettings.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            updateTemaInBase()
            Exit.setTextColor(Color.parseColor("#b2b2b2"))
            Exit.background=null
            switchTemnayaTema.setTextColor(Color.parseColor("#b2b2b2"))

        }
        if(isChecked==false)
        {

            activity!!.toolbar.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivity.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivityBottom.setBackgroundResource(R.color.LiteThema)
            activity!!.ConstraintSettings.setBackgroundResource(R.drawable.fon_na_fragment)
            Exit.setBackgroundResource(R.drawable.megaoval)
            Exit.setTextColor(Color.parseColor("#000000"))
            switchTemnayaTema.setTextColor(Color.parseColor("#000000"))
            STORAGE.Tema=isChecked
            updateTemaInBase()

        }


    }

    fun updateTemaInBase(){
        val dateMap =
            mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
        dateMap[CHIELD_TEMA] = STORAGE.Tema
        REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID).updateChildren(dateMap)

    }
}



package com.example.povar.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.activity.RegistryActivity
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*


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
        lang()
        switchTemnayaTema?.isChecked = STORAGE.Tema
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)
        hideSettings(activity!!)
        hideAddButton(activity!!)
        initTema()
        switchTemnayaTema.setOnCheckedChangeListener(this)

        Exit.setOnClickListener{
            exit()
        }
        Language.setOnClickListener {
            langSmena()
        }


    }

    override fun onStop() {
        super.onStop()

    }



    fun langSmena()
    {
        if(STORAGE.Language=="Rus") {
            STORAGE.Language = "Eng"
            showToast("English language")
        }
        else
        { STORAGE.Language="Rus"
                lang()
                showToast("Русский язык")
            }
        lang()
    }
    fun lang()
    {

        if(STORAGE.Language=="Eng") {
            activity!!.toolbar.setTitle("Settings")
            switchTemnayaTema.setText("Dark Them ")
            Language.setText("  Choose language (Rus)  ")
            Exit.setText(" Exit ")
            activity!!.myRecept.setText(" My recepts ")
            activity!!.AllRecept.setText(" All recepts ")
            activity!!.dopFunc.setText(" Calories ")
           activity!!.SearchRecept.setHint(" Search ")

        }
        else
        {
            activity!!.toolbar.setTitle("Настройки")
            switchTemnayaTema.setText("Темная тема ")
            Language.setText("  Сменить язык (Eng)  ")
            Exit.setText(" Выход ")
            activity!!.myRecept.setText(" Мои рецепты ")
            activity!!.AllRecept.setText(" Все рецепты ")
            activity!!.dopFunc.setText(" Калории ")
            activity!!.SearchRecept.setHint(" Поиск ")

        }
    }
    fun exit(){
        STORAGE.Tema=false
        STORAGE.Language="Rus"
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContext=""
        STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain=""
        startActivity(Intent(activity, RegistryActivity::class.java))
    }
    fun initTema()
    {
        if (STORAGE.Tema==true)
        {
            ConstraintSettings.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            Exit.setTextColor(Color.parseColor("#b2b2b2"))
            Exit.setBackgroundResource(R.drawable.megaoval_anim_dark)
            switchTemnayaTema.setTextColor(Color.parseColor("#b2b2b2"))
            Language.setTextColor(Color.parseColor("#b2b2b2"))
            Language.setBackgroundResource(R.drawable.megaoval_anim_dark)
        }
        else
        {
            ConstraintSettings.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
            Exit.setTextColor(Color.parseColor("#000000"))
            Exit.setBackgroundResource(R.drawable.megaoval_anim)
            switchTemnayaTema.setTextColor(Color.parseColor("#000000"))
            Language.setTextColor(Color.parseColor("#000000"))
            Language.setBackgroundResource(R.drawable.megaoval_anim)
        }
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
            Exit.setBackgroundResource(R.drawable.megaoval_anim_dark)
            switchTemnayaTema.setTextColor(Color.parseColor("#b2b2b2"))
            Language.setTextColor(Color.parseColor("#b2b2b2"))
            Language.setBackgroundResource(R.drawable.megaoval_anim_dark)

        }
        if(isChecked==false)
        {

            activity!!.toolbar.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivity.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivityBottom.setBackgroundResource(R.color.LiteThema)
            activity!!.ConstraintSettings.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
            Exit.setBackgroundResource(R.drawable.megaoval_anim)
            Exit.setTextColor(Color.parseColor("#000000"))
            switchTemnayaTema.setTextColor(Color.parseColor("#000000"))
            Language.setTextColor(Color.parseColor("#000000"))
            Language.setBackgroundResource(R.drawable.megaoval_anim)
            STORAGE.Tema=isChecked
            updateTemaInBase()
        }
    }

    fun updateTemaInBase(){
        val dateMap =
            mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
        dateMap[CHIELD_USER_TEMA] = STORAGE.Tema
        REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID).updateChildren(dateMap)

    }
}



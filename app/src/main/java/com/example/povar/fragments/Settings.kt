package com.example.povar.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.Toast
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
        SpinnerComplexityUser.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                val choose = resources.getStringArray(R.array.complexity)

                if(choose[selectedItemPosition]=="Не выбрано" ||choose[selectedItemPosition]=="None")
                    STORAGE.complexityUser="None"
                else if(choose[selectedItemPosition]=="Новичок" || choose[selectedItemPosition]=="Newbie")
                    STORAGE.complexityUser="Новичок"
                else if(choose[selectedItemPosition]=="Студент" || choose[selectedItemPosition]=="Student")
                    STORAGE.complexityUser="Студент"
                else if(choose[selectedItemPosition]=="Повар" || choose[selectedItemPosition]=="Cook")
                    STORAGE.complexityUser="Повар"
                else if(choose[selectedItemPosition]=="Шеф" || choose[selectedItemPosition]=="Chef")
                    STORAGE.complexityUser="Шеф"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        })
        init()
    }

    private fun init() {
        if(STORAGE.complexityUser=="None")
            SpinnerComplexityUser.setSelection(0)
        if(STORAGE.complexityUser=="Новичок")
            SpinnerComplexityUser.setSelection(1)
        if(STORAGE.complexityUser=="Студент")
            SpinnerComplexityUser.setSelection(2)
        if(STORAGE.complexityUser=="Повар")
            SpinnerComplexityUser.setSelection(3)
        if(STORAGE.complexityUser=="Шеф")
            SpinnerComplexityUser.setSelection(4)
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
        temaSpinner()
        init()
        if(STORAGE.Language=="Eng") {
            activity!!.toolbar.setTitle("Settings")
            switchTemnayaTema.setText("Dark Theme ")
            Language.setText("  Choose language (Rus)  ")
            Exit.setText(" Exit ")
            TextSpinnerComplexityUser.setText(" Complexity of dishes ")
            activity!!.myRecept.setText(" My recipes ")
            activity!!.AllRecept.setText(" All recipes ")
            activity!!.dopFunc.setText(" Calories ")
           activity!!.SearchRecept.setHint(" Search ")

        }
        else
        {
            activity!!.toolbar.setTitle("Настройки")
            switchTemnayaTema.setText("Темная тема ")
            Language.setText("  Сменить язык (Eng)  ")
            Exit.setText(" Выход ")
            TextSpinnerComplexityUser.setText(" Сложность блюд ")
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
    fun temaSpinner(){
        if (STORAGE.Language =="Rus") {
        if(STORAGE.Tema==true) {
            var adapter = ArrayAdapter.createFromResource(
                activity!!,
                R.array.complexity,
                R.layout.spinner_text_color_dark_droped
            )
            adapter.setDropDownViewResource(R.layout.spinner_text_color_dark)
            SpinnerComplexityUser.adapter = adapter
        }
        else
            {
                    var adapter = ArrayAdapter.createFromResource(
                        activity!!,
                        R.array.complexity,
                        R.layout.spinner_text_color_lite_droped
                    )
                    adapter.setDropDownViewResource(R.layout.spinner_text_color_lite)
                    SpinnerComplexityUser.adapter = adapter
            }
        }
        else{
            if(STORAGE.Tema==true) {
                var adapter = ArrayAdapter.createFromResource(
                    activity!!,
                    R.array.complexity_angl,
                    R.layout.spinner_text_color_dark_droped
                )
                adapter.setDropDownViewResource(R.layout.spinner_text_color_dark)
                SpinnerComplexityUser.adapter = adapter
            }
            else
            {
                var adapter = ArrayAdapter.createFromResource(
                    activity!!,
                    R.array.complexity_angl,
                    R.layout.spinner_text_color_lite_droped
                )
                adapter.setDropDownViewResource(R.layout.spinner_text_color_lite)
                SpinnerComplexityUser.adapter = adapter
            }
        }
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
            TextSpinnerComplexityUser.setTextColor(Color.parseColor("#b2b2b2"))
        }
        else
        {
            ConstraintSettings.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
            Exit.setTextColor(Color.parseColor("#000000"))
            Exit.setBackgroundResource(R.drawable.megaoval_anim)
            switchTemnayaTema.setTextColor(Color.parseColor("#000000"))
            Language.setTextColor(Color.parseColor("#000000"))
            Language.setBackgroundResource(R.drawable.megaoval_anim)
            TextSpinnerComplexityUser.setTextColor(Color.parseColor("#000000"))
        }
        temaSpinner()
        init()
    }

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCheckedChanged(Component: CompoundButton?, isChecked: Boolean) {

        if(isChecked==true) {

            STORAGE.Tema=isChecked
            updateTemaInBase()
            activity!!.LinearActivity.setBackgroundResource(R.color.DarkThema)
            activity!!.LinearActivityBottom.setBackgroundResource(R.color.DarkThema)
            activity!!.toolbar.setBackgroundResource(R.color.DarkThema)
            activity!!.ConstraintSettings.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            Exit.setTextColor(Color.parseColor("#b2b2b2"))
            Exit.setBackgroundResource(R.drawable.megaoval_anim_dark)
            switchTemnayaTema.setTextColor(Color.parseColor("#b2b2b2"))
            Language.setTextColor(Color.parseColor("#b2b2b2"))
            Language.setBackgroundResource(R.drawable.megaoval_anim_dark)
            TextSpinnerComplexityUser.setTextColor(Color.parseColor("#b2b2b2"))


        }
        if(isChecked==false)
        {
            STORAGE.Tema=isChecked
            updateTemaInBase()
            activity!!.toolbar.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivity.setBackgroundResource(R.color.LiteThema)
            activity!!.LinearActivityBottom.setBackgroundResource(R.color.LiteThema)
            activity!!.ConstraintSettings.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
            Exit.setBackgroundResource(R.drawable.megaoval_anim)
            Exit.setTextColor(Color.parseColor("#000000"))
            switchTemnayaTema.setTextColor(Color.parseColor("#000000"))
            Language.setTextColor(Color.parseColor("#000000"))
            Language.setBackgroundResource(R.drawable.megaoval_anim)
            TextSpinnerComplexityUser.setTextColor(Color.parseColor("#000000"))

        }
        temaSpinner()
        init()
    }

    fun updateTemaInBase(){
        val dateMap =
            mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
        dateMap[CHIELD_USER_TEMA] = STORAGE.Tema
        REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID).updateChildren(dateMap)

    }

}



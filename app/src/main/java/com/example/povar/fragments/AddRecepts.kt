package com.example.povar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*

class fragment2 : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)

    }

    override fun onStart() {
        super.onStart()

        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)

        button_add.setOnClickListener { addRecept() }  // если клик по кнопке вызвать метод
    }

    private fun addRecept() {

        if (EditTextNameAdd.text.toString().isEmpty()) {  // если пусто в названии блюда, то
            Toast.makeText(activity, "Введите название бюда", Toast.LENGTH_SHORT).show()

        } else {
            if (EditTextFormulaAdd.text.toString().isEmpty()) // если пусто в  рецепте блюда, то
            {
                Toast.makeText(activity, "Введите рецепт блюда", Toast.LENGTH_SHORT).show()
            } else {

                val name =
                    EditTextNameAdd.text.toString() //запись в перемнную из ЕдитТекст
                val ingridients =
                    EditTextIngridientAdd.text.toString() //запись в перемнную из ЕдитТекст
                val formula =
                    EditTextFormulaAdd.text.toString()// запись в перемнную из ЕдитТекст

                val dateMap =
                    mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                var ID=name+STORAGE.ID
                dateMap[CHIELD_RECEPT_ID] = ID
                dateMap[CHIELD_RECEPT_NAME] = name
                dateMap[CHIELD_RECEPT_INGRIDIENTS]=ingridients
                dateMap[CHIELD_RECEPT_FORMULA] = formula
                dateMap[CHIELD_RECEPT_USER_ID]=STORAGE.ID


                REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(ID).updateChildren(dateMap)
                ////////////////////////////////////////////////////////////////////////
                val dateMapUser =
                    mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                STORAGE.counter_recept=STORAGE.counter_recept+1
                dateMapUser[CHIELD_COUNTER_RECEPT]=STORAGE.counter_recept
                REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID).updateChildren(dateMapUser)

                     replaceFragment(fragment2())

                Toast.makeText(activity, "Блюдо успешно добавлено..", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add, container, false)
    }


}



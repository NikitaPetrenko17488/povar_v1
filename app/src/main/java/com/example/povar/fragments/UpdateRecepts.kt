package com.example.povar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.fragment_update.*



class fragment3 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        button_update.setOnClickListener { updateRecept() }  // если клик по кнопке вызвать метод
    }

    private fun updateRecept() {
        if (EditTextNameUpdate.text.toString().isEmpty()) {  // если пусто в названии блюда, то
            Toast.makeText(activity, "Введите название бюда", Toast.LENGTH_SHORT).show()

        } else {
            if (EditTextFormulaUpdate.text.toString().isEmpty()) // если пусто в  рецепте блюда, то
            {
                Toast.makeText(activity, "Введите рецепт блюда", Toast.LENGTH_SHORT).show()
            } else {

                val name =
                    EditTextNameUpdate.text.toString() //запись в перемнную из ЕдитТекст
                val ingridients =
                    EditTextIngridientUpdate.text.toString() //запись в перемнную из ЕдитТекст
                val formula =
                    EditTextFormulaUpdate.text.toString()// запись в перемнную из ЕдитТекст
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap =
                    mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                dateMap[CHIELD_RECEPT_ID]
                dateMap[CHIELD_RECEPT_NAME] = name
                dateMap[CHIELD_RECEPT_INGRIDIENTS] = ingridients
                dateMap[CHIELD_RECEPT_FORMULA] = formula


                REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(name).updateChildren(dateMap)

                replaceFragment(fragment1())

                Toast.makeText(activity, "Блюдо успешно обновлено..", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false)

    }


}
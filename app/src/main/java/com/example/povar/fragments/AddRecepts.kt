package com.example.povar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.fragment_add.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fragment2 : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onStart() {
        super.onStart()
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
                dateMap[CHIELD_RECEPT_ID]
                dateMap[CHIELD_RECEPT_NAME] = name
                dateMap[CHIELD_RECEPT_INGRIDIENTS]=ingridients
                dateMap[CHIELD_RECEPT_FORMULA] = formula


                REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(name).updateChildren(dateMap)

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

    companion object {

        fun newInstance(param1: String, param2: String) =
            fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}



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
import kotlinx.android.synthetic.main.fragment_translate_recept.*


class TranslateRecept : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translate_recept, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()

        ButtonAddTranslate.setOnClickListener {
            addTranslate()
        }


    }

    private fun addTranslate() {
        if (EditTranslateName.text.toString().isEmpty()) {  // если пусто в названии блюда, то
            showToast("Переведите название блюда")

        } else {
            if (EditTranslateIngridient.text.toString().isEmpty()) // если пусто в  рецепте блюда, то
            {
                showToast("Переведите ингридиенты блюда")
            } else {
                if(EditTranslateFormula.text.toString().isEmpty())
                    {showToast("Переведите рецепт блюда")}
                else
                {
                    val dateMap =
                        mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд

                    dateMap[CHIELD_RECEPT_ANGNAME] = EditTranslateName.text.toString()
                    dateMap[CHIELD_RECEPT_ANGINGRIDIENS] = EditTranslateIngridient.text.toString()
                    dateMap[CHIELD_RECEPT_ANGFORMULA] = EditTranslateFormula.text.toString()


                    REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID).updateChildren(dateMap)
                    ////////////////////////////////////////////////////////////////////////

                   replaceFragment3(Translater())

                    showToast("Блюдо переведено")
                }

            }
        }
    }

    fun init()
    {
        ImageViewTranslate.downloadSetImage(STORAGE_FOR_RECYCLE_RECEPT.photo)
        TextNameTranslate.setText(STORAGE_FOR_RECYCLE_RECEPT.name)
        TextIngridientTranslate.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridients)
        TextFormulaTranslate.setText(STORAGE_FOR_RECYCLE_RECEPT.formula)

    }

}
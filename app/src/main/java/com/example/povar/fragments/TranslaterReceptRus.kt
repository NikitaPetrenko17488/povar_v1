package com.example.povar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.fragment_translate_recept.*
import kotlinx.android.synthetic.main.fragment_translater_recept_rus.*


class TranslaterReceptRus : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translater_recept_rus, container, false)
    }

    override fun onStart() {
        super.onStart()

        init()

        ButtonAddTranslateRus.setOnClickListener {
            addTranslate()
        }
    }

    private fun addTranslate() {
        if (EditTranslateNameRus.text.toString().isEmpty()) {  // если пусто в названии блюда, то
            showToast("Переведите название блюда")

        } else {
            if (EditTranslateIngridientRus.text.toString().isEmpty()) // если пусто в  рецепте блюда, то
            {
                showToast("Переведите ингридиенты блюда")
            } else {
                if(EditTranslateFormulaRus.text.toString().isEmpty())
                {showToast("Переведите рецепт блюда")}
                else
                {
                    val dateMap =
                        mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд

                    dateMap[CHIELD_RECEPT_NAME] = EditTranslateNameRus.text.toString()
                    dateMap[CHIELD_RECEPT_INGRIDIENTS] = EditTranslateIngridientRus.text.toString()
                    dateMap[CHIELD_RECEPT_FORMULA] = EditTranslateFormulaRus.text.toString()


                    REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID).updateChildren(dateMap)
                    ////////////////////////////////////////////////////////////////////////

                    findNavController().navigate(R.id.translater)
                    //replaceFragment3(Translater())

                    showToast("Блюдо переведено")
                }

            }
        }
    }

    private fun init()
    {
        ImageViewTranslateRus.downloadSetImage(STORAGE_FOR_RECYCLE_RECEPT.photo)
        TextNameTranslateRus.setText(STORAGE_FOR_RECYCLE_RECEPT.nameEng)
        TextIngridientTranslateRus.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridientsEng)
        TextFormulaTranslateRus.setText(STORAGE_FOR_RECYCLE_RECEPT.formulaEng)

    }
}
package com.example.povar.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.list_item_recept.*

class fragment2 : Fragment() {

    var Mat=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mat=false

        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)
        hideAddButton(activity!!)

    }

    override fun onStart() {
        super.onStart()
         Mat=false

        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)
        hideAddButton(activity!!)

        tema()
        language()
        button_add.setOnClickListener { addRecept() }  // если клик по кнопке вызвать метод
    }



    override fun onStop() {
        super.onStop()
         Mat=false
    }

    private fun addRecept() {

        if (EditTextNameAdd.text.toString().isEmpty()) {  // если пусто в названии блюда, то
           if(STORAGE.Language=="Rus")
                showToast("Введите название блюда")
            else
               showToast("Enter the name of the dish")

        } else {
            if (EditTextFormulaAdd.text.toString().isEmpty()) // если пусто в  рецепте блюда, то
            {
                if(STORAGE.Language=="Rus")
                    showToast("Введите рецепт блюда")
                else
                    showToast("Enter your recipe")
            } else {

              ProverkaMat()

                if(Mat==true)
                {
                    if(STORAGE.Language=="Rus")
                        showToast("Удалите запрещенные слова!")
                    else
                        showToast("Remove the forbidden words!")
                }
                else {

                    val name =
                        EditTextNameAdd.text.toString() //запись в перемнную из ЕдитТекст
                    val ingridients =
                        EditTextIngridientAdd.text.toString() //запись в перемнную из ЕдитТекст
                    val formula =
                        EditTextFormulaAdd.text.toString()// запись в перемнную из ЕдитТекст

                    val dateMap =
                        mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                    val dateMapEng =
                        mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                    var ID = name + STORAGE.ID
                    dateMap[CHIELD_RECEPT_ID] = ID
                    dateMap[CHIELD_RECEPT_NAME] = name
                    dateMap[CHIELD_RECEPT_INGRIDIENTS] = ingridients
                    dateMap[CHIELD_RECEPT_FORMULA] = formula
                    dateMap[CHIELD_RECEPT_USER_ID] = STORAGE.ID
                    dateMap[CHIELD_RECEPT_CHEK]=false

                    dateMapEng[CHIELD_RECEPT_ID] = ID
                    dateMapEng[CHIELD_RECEPT_ANGNAME] =name
                    dateMapEng[CHIELD_RECEPT_ANGINGRIDIENS] = ingridients
                    dateMapEng[CHIELD_RECEPT_ANGFORMULA] = formula
                    dateMapEng[CHIELD_RECEPT_USER_ID] = STORAGE.ID
                    dateMap[CHIELD_RECEPT_CHEK]=false

                    if(STORAGE.Language=="Rus")
                        REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(ID).updateChildren(dateMap)
                    else
                        REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(ID).updateChildren(dateMapEng)
                    ////////////////////////////////////////////////////////////////////////
                    val dateMapUser =
                        mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                    STORAGE.counter_recept = STORAGE.counter_recept + 1
                    dateMapUser[CHIELD_COUNTER_RECEPT] = STORAGE.counter_recept
                    REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID)
                        .updateChildren(dateMapUser)

                    replaceFragment(fragment5())

                    if (STORAGE.Language=="Rus")
                        showToast("Блюдо успешно добавлено ...")
                    else
                        showToast("Food added successfully ...")
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add, container, false)
    }

     fun ProverkaMat()
    {
        Mat=false
        var nameUp:String=EditTextNameAdd.text.toString().toUpperCase()
        var ingridientsUP:String=EditTextIngridientAdd.text.toString().toUpperCase()
        var formulaUP:String=EditTextFormulaAdd.text.toString().toUpperCase()

        var arrString:Array<String> = arrayOf("бля","хуй","пизда","ебал","говно","ебал","ебать","лох","долбаеб","хуесос","пидорас","пидор","блять","хуйня","нахуй")

        var i=0
        while(i<15) {
            var MatStringUp = arrString[i].toUpperCase()

            if (nameUp.contains(MatStringUp) || ingridientsUP.contains(MatStringUp) || formulaUP.contains(MatStringUp))
                Mat = true
            i++

        }

    }

private fun tema()
{
    if(STORAGE.Tema==true)
    {
        Constraint_layout_add.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
        EditTextNameAdd.setTextColor(Color.parseColor("#b2b2b2"))
        EditTextIngridientAdd.setTextColor(Color.parseColor("#b2b2b2"))
        EditTextFormulaAdd.setTextColor(Color.parseColor("#b2b2b2"))

        EditTextNameAdd.setHintTextColor(Color.parseColor("#b2b2b2"))
        EditTextIngridientAdd.setHintTextColor(Color.parseColor("#b2b2b2"))
        EditTextFormulaAdd.setHintTextColor(Color.parseColor("#b2b2b2"))

        button_add.setBackgroundResource(R.drawable.megaoval_anim_dark)
        button_add.setTextColor(Color.parseColor("#b2b2b2"))
    }
    else
    {
        Constraint_layout_add.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
        EditTextNameAdd.setTextColor(Color.parseColor("#000000"))
        EditTextIngridientAdd.setTextColor(Color.parseColor("#000000"))
        EditTextFormulaAdd.setTextColor(Color.parseColor("#000000"))

        EditTextNameAdd.setHintTextColor(Color.parseColor("#000000"))
        EditTextIngridientAdd.setHintTextColor(Color.parseColor("#000000"))
        EditTextFormulaAdd.setHintTextColor(Color.parseColor("#000000"))

        button_add.setBackgroundResource(R.drawable.megaoval_anim)
        button_add.setTextColor(Color.parseColor("#000000"))
    }

}
    private fun language() {
        if(STORAGE.Language=="Eng") {
            activity!!.toolbar.setTitle("Add recipe")
            EditTextNameAdd.setHint(" Dish name ")
            EditTextIngridientAdd.setHint(" Ingredients ")
            EditTextFormulaAdd.setHint(" Recipe ")
            button_add.setText(" Add ")
        }
        else{
            activity!!.toolbar.setTitle("Добавить рецепт")
            EditTextNameAdd.setHint(" Название блюда ")
            EditTextIngridientAdd.setHint(" Ингридиенты ")
            EditTextFormulaAdd.setHint(" Рецепт ")
            button_add.setText(" Добавить ")
        }
    }
}



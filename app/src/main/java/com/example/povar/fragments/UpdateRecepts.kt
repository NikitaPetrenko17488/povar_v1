package com.example.povar.fragments

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.os.Build.ID
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.povar.R
import com.example.povar.activity.MainActivity
import com.example.povar.ui.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.list_item_recept.*


class fragment3 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        Avotzapolnenie()
        button_update.setOnClickListener { updateRecept() }  // если клик по кнопке вызвать метод

        image_src.setOnClickListener{ changePhotoRecept() }
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

                val dateMap =
                    mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд

                dateMap[CHIELD_RECEPT_ID]= STORAGE_FOR_RECYCLE_RECEPT.ID
                dateMap[CHIELD_RECEPT_NAME] = name
                dateMap[CHIELD_RECEPT_INGRIDIENTS] = ingridients
                dateMap[CHIELD_RECEPT_FORMULA] = formula


                REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID).updateChildren(dateMap)

                replaceFragment(fragment5())

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
fun Avotzapolnenie()
{
    Log.d(STORAGE_FOR_RECYCLE_RECEPT.name,"xuy")

    var avtozagrNameUpdateRecept=
        activity!!.findViewById<EditText>(R.id.EditTextNameUpdate)
    avtozagrNameUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.name)
    var avtozagrIngridientsUpdateRecept=
        activity!!.findViewById<EditText>(R.id.EditTextIngridientUpdate)
    avtozagrIngridientsUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridients)
    var avtozagrFormulaUpdateRecept=
        activity!!.findViewById<EditText>(R.id.EditTextFormulaUpdate)
    avtozagrFormulaUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.formula)
}

    override fun onStop() {
        super.onStop()

     showUserNameAdnImage(activity!!)
    }
}



private fun changePhotoRecept() {
//    CropImage.activity()
//        .setAspectRatio(1,1)
//        .setRequestedSize(600,600)
//        .setCropShape(CropImageView.CropShape.OVAL)
//        .start(activity as MainActivity)
}
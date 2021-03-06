package com.example.povar.fragments


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.povar.R
import com.example.povar.activity.MainActivity
import com.example.povar.ui.*
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*


class fragment3 : Fragment() {

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

        Avotzapolnenie()
        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)
        hideAddButton(activity!!)

        tema()
        language()

        button_update.setOnClickListener { updateRecept() }  // если клик по кнопке вызвать метод
        ImageUpdateRecept.setOnClickListener {
            changePhotoRecept()
        }

    }

    private fun changePhotoRecept() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(600, 600)
            .start((activity as MainActivity), this)
    }

    private fun updateRecept() {


        if (EditTextNameUpdate.text.toString().isEmpty()) {  // если пусто в названии блюда, то

            if(STORAGE.Language=="Rus")
                showToast("Введите название блюда")
            else
                showToast("Enter the name of the dish")

        } else {
            if (EditTextFormulaUpdate.text.toString().isEmpty()) // если пусто в  рецепте блюда, то
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
                        EditTextNameUpdate.text.toString() //запись в перемнную из ЕдитТекст
                    val ingridients =
                        EditTextIngridientUpdate.text.toString() //запись в перемнную из ЕдитТекст
                    val formula =
                        EditTextFormulaUpdate.text.toString()// запись в перемнную из ЕдитТекст

                    val dateMap = mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                    val dateMapEng = mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд

                    // dateMap[CHIELD_RECEPT_ID]= STORAGE_FOR_RECYCLE_RECEPT.ID+STORAGE.ID
                    dateMap[CHIELD_RECEPT_NAME] = name
                    dateMap[CHIELD_RECEPT_INGRIDIENTS] = ingridients
                    dateMap[CHIELD_RECEPT_FORMULA] = formula

                    dateMapEng[CHIELD_RECEPT_ANGNAME] =name
                    dateMapEng[CHIELD_RECEPT_ANGINGRIDIENS] = ingridients
                    dateMapEng[CHIELD_RECEPT_ANGFORMULA] = formula

                    if(STORAGE.Language=="Rus")
                        REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID).updateChildren(dateMap)
                    else
                        REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID).updateChildren(dateMapEng)



                    if (STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain == "Main") {
                        replaceFragment(fragment5())
                    }
                        if(STORAGE.Language=="Rus")
                            showToast("Блюдо увпешно обновлено ...")
                        else
                            showToast("The dish has been hastily updated ...")
                }
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

    var avtozagrNameUpdateRecept=
        activity!!.findViewById<EditText>(R.id.EditTextNameUpdate)
    var avtozagrIngridientsUpdateRecept=
        activity!!.findViewById<EditText>(R.id.EditTextIngridientUpdate)
    var avtozagrFormulaUpdateRecept=
        activity!!.findViewById<EditText>(R.id.EditTextFormulaUpdate)
    var avtozagrImageUpdateRecept=
        activity!!.findViewById<ImageView>(R.id.ImageUpdateRecept)
    avtozagrImageUpdateRecept.downloadSetImage(STORAGE_FOR_RECYCLE_RECEPT.photo)

    if(STORAGE.Language=="Rus") {
        avtozagrNameUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.name)
        avtozagrIngridientsUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridients)
        avtozagrFormulaUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.formula)
    }
    else
    {
        avtozagrNameUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.nameEng)
        avtozagrIngridientsUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridientsEng)
        avtozagrFormulaUpdateRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.formulaEng)
    }


}

    override fun onStop() {
        super.onStop()
        Mat=false

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==Activity.RESULT_OK &&  data!=null)
        {
            val uri = CropImage.getActivityResult(data).uri
            val path= REF_STORAGE_ROOT.child(FOLDER_IMAGE_RECEPT)
                .child(STORAGE_FOR_RECYCLE_RECEPT.ID)
            path.putFile(uri).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    path.downloadUrl.addOnCompleteListener{
                        if(it.isSuccessful){
                            val photoUrl=it.result.toString()
                            STORAGE_FOR_RECYCLE_RECEPT.photo=photoUrl
                            REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID)
                                .child(CHIELD_PHOTO_RECEPT_SRC).setValue(photoUrl).addOnCompleteListener { task2->
                                    if(task2.isSuccessful)
                                    {


                                    }
                                }

                        }

                    }
                }

            }
        }

    }


    fun ProverkaMat()
    {
        Mat=false
        var nameUp:String=EditTextNameUpdate.text.toString().toUpperCase()
        var ingridientsUP:String=EditTextIngridientUpdate.text.toString().toUpperCase()
        var formulaUP:String=EditTextFormulaUpdate.text.toString().toUpperCase()

        var arrString:Array<String> = arrayOf("бля","хуй","пизда","ебал","говно","ебал","ебать","лох","долбаеб","хуесос","пидорас","пидор","блять","хуйня","нахуй")

        var i=0
        while(i<15) {
            var MatStringUp = arrString[i].toUpperCase()

            if (nameUp.contains(MatStringUp) || ingridientsUP.contains(MatStringUp) || formulaUP.contains(MatStringUp))
                Mat = true
            i++

        }

    }


    private fun tema() {
        if (STORAGE.Tema == true) {
            Constraint_layout_update.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            EditTextNameUpdate.setTextColor(Color.parseColor("#b2b2b2"))
            EditTextIngridientUpdate.setTextColor(Color.parseColor("#b2b2b2"))
            EditTextFormulaUpdate.setTextColor(Color.parseColor("#b2b2b2"))

            EditTextNameUpdate.setHintTextColor(Color.parseColor("#b2b2b2"))
            EditTextIngridientUpdate.setHintTextColor(Color.parseColor("#b2b2b2"))
            EditTextFormulaUpdate.setHintTextColor(Color.parseColor("#b2b2b2"))

            button_update.setBackgroundResource(R.drawable.megaoval_anim_dark)
            button_update.setTextColor(Color.parseColor("#b2b2b2"))
        } else {
            Constraint_layout_update.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
            EditTextNameUpdate.setTextColor(Color.parseColor("#000000"))
            EditTextIngridientUpdate.setTextColor(Color.parseColor("#000000"))
            EditTextFormulaUpdate.setTextColor(Color.parseColor("#000000"))

            EditTextNameUpdate.setHintTextColor(Color.parseColor("#000000"))
            EditTextIngridientUpdate.setHintTextColor(Color.parseColor("#000000"))
            EditTextFormulaUpdate.setHintTextColor(Color.parseColor("#000000"))

            button_update.setBackgroundResource(R.drawable.megaoval_anim)
            button_update.setTextColor(Color.parseColor("#000000"))
        }
    }

    private fun language() {
        if(STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain=="Main") {
            if (STORAGE.Language == "Eng") {
                activity!!.toolbar.setTitle("Edit recipe")
                EditTextNameUpdate.setHint(" Dish name ")
                EditTextIngridientUpdate.setHint(" Ingredients ")
                EditTextFormulaUpdate.setHint(" Recipe ")
                button_update.setText(" Edit ")
            } else {
                activity!!.toolbar.setTitle("Редактировать рецепт")
                EditTextNameUpdate.setHint(" Название блюда ")
                EditTextIngridientUpdate.setHint(" Ингридиенты ")
                EditTextFormulaUpdate.setHint(" Рецепт ")
                button_update.setText(" Изменить ")
            }
        }
    }

}








package com.example.povar.fragments


import android.app.Activity
import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_update.*


class fragment3 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)

    }

    override fun onStart() {
        super.onStart()
        Avotzapolnenie()

        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)

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

               // dateMap[CHIELD_RECEPT_ID]= STORAGE_FOR_RECYCLE_RECEPT.ID+STORAGE.ID
                dateMap[CHIELD_RECEPT_NAME] = name
                dateMap[CHIELD_RECEPT_INGRIDIENTS] = ingridients
                dateMap[CHIELD_RECEPT_FORMULA] = formula
                REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID).updateChildren(dateMap)


                if(STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain=="Main") {
                    replaceFragment(fragment5())
                }

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
    var avtozagrImageUpdateRecept=
        activity!!.findViewById<ImageView>(R.id.ImageUpdateRecept)
    avtozagrImageUpdateRecept.downloadSetImage(STORAGE_FOR_RECYCLE_RECEPT.photo)
}

    override fun onStop() {
        super.onStop()

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

}








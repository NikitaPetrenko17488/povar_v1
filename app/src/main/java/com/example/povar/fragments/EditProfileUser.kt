package com.example.povar.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.povar.R
import com.example.povar.activity.MainActivity
import com.example.povar.ui.*
import com.example.povar.ui.STORAGE.Companion.name
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.list_item_recept.*


class EditProfileUser : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name=STORAGE.name

        showSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)
        hideAddButton(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onStart() {
        super.onStart()

        showSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)
        hideAddButton(activity!!)


        tema()
        language()

        var login =
            activity!!.findViewById<EditText>(R.id.LoginInProfileEdit)
        var name=
            activity!!.findViewById<EditText>(R.id.NameInProfileEdit)
        ImageViewProfileEdit.downloadSetImage(STORAGE.photo)

        ImageViewProfileEdit.setOnClickListener{

            changePhotoUser()

        }

        SaveProfile.setOnClickListener{

            STORAGE.login=login.text.toString()
            STORAGE.name=name.text.toString()

           updateUser()
        }

        login.setText(STORAGE.login)
        name.setText(STORAGE.name)
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600,600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(activity as MainActivity,this)
    }



    override fun onStop() {
        super.onStop()
    }

    private fun updateUser() {
        if (NameInProfileEdit.text.toString().isEmpty()) {  // если пусто в названии , то
            Toast.makeText(activity, "Введите имя ", Toast.LENGTH_SHORT).show()

        } else {
            if (LoginInProfileEdit.text.toString().isEmpty()) // если пусто в  рецепте , то
            {
                Toast.makeText(activity, "Введите логин", Toast.LENGTH_SHORT).show()
            } else {

                var names=NameInProfileEdit.text.toString()
                var logins =LoginInProfileEdit.text.toString()

                val dateMap =
                    mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                dateMap[CHIELD_USER_NAME]=names.toString()
                dateMap[CHIELD_USER_LOGIN] = logins.toString()
                dateMap[CHIELD_USER_ID]=STORAGE.ID

                REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID).updateChildren(dateMap)

                Toast.makeText(activity, "Данные успешно обновлены..", Toast.LENGTH_SHORT).show()
                replaceFragment(ProfileUsers())
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==Activity.RESULT_OK &&  data!=null)
        {
            val uri = CropImage.getActivityResult(data).uri
            val path= REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(STORAGE.ID)
            path.putFile(uri).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    path.downloadUrl.addOnCompleteListener{
                        if(it.isSuccessful){
                            val photoUrl=it.result.toString()
                            STORAGE.photo=photoUrl
                            REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID)
                                .child(CHIELD_PHOTO_USER_SRC).setValue(photoUrl).addOnCompleteListener {task2->
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

    private fun tema() {
        if (STORAGE.Tema == true) {
            Constraint_layout_edit_profile_users.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            NameInProfileEdit.setTextColor(Color.parseColor("#b2b2b2"))
            LoginInProfileEdit.setTextColor(Color.parseColor("#b2b2b2"))
            NameTextInProfieEdit.setTextColor(Color.parseColor("#b2b2b2"))
            LoginTextInProfieEdit.setTextColor(Color.parseColor("#b2b2b2"))

            NameInProfileEdit.setHintTextColor(Color.parseColor("#b2b2b2"))
            LoginInProfileEdit.setHintTextColor(Color.parseColor("#b2b2b2"))

            SaveProfile.setTextColor(Color.parseColor("#b2b2b2"))
            SaveProfile.setBackgroundResource(R.drawable.megaoval_anim_dark)


        } else {
            Constraint_layout_edit_profile_users.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
            NameInProfileEdit.setTextColor(Color.parseColor("#000000"))
            LoginInProfileEdit.setTextColor(Color.parseColor("#000000"))
            NameTextInProfieEdit.setTextColor(Color.parseColor("#000000"))
            LoginTextInProfieEdit.setTextColor(Color.parseColor("#000000"))

            NameInProfileEdit.setHintTextColor(Color.parseColor("#000000"))
            LoginInProfileEdit.setHintTextColor(Color.parseColor("#000000"))

            SaveProfile.setTextColor(Color.parseColor("#000000"))
            SaveProfile.setBackgroundResource(R.drawable.megaoval_anim)
        }
    }
    private fun language()
    {
        if(STORAGE.Language=="Eng")
        {
            activity!!.toolbar.setTitle("Edit profile")
            NameTextInProfieEdit.setText("Name - ")
            LoginTextInProfieEdit.setText("Login - ")
            SaveProfile.setText(" Save ")
        }
        else
        {
            activity!!.toolbar.setTitle("Редактировать профиль")
            NameTextInProfieEdit.setText("Имя - ")
            LoginTextInProfieEdit.setText("Логин - ")
            SaveProfile.setText(" Сохранить ")
        }

    }
}
package com.example.povar.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R
import com.example.povar.fragments.EditProfileUser
import com.example.povar.fragments.fragment5
import com.example.povar.ui.*
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :AppCompatActivity() {


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bd: BitmapDrawable =resources.getDrawable(R.drawable.user) as BitmapDrawable
        var name_users= STORAGE.name
        var name =findViewById<TextView>(R.id.textViewNameforActivityMain)
        name.text=name_users
        var circleImage =findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
        circleImage.downloadSetImage(STORAGE.photo)
        myRecept
        initFirebase()

    }

    override fun onStart() {
        super.onStart()

        var name_users= STORAGE.name
        var name =findViewById<TextView>(R.id.textViewNameforActivityMain)
        name.text=name_users
        var circleImage =findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)

        // получаем navController
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        var navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        textViewNameforActivityMain.setOnClickListener{
            name.text=null
            circleImage.setImageDrawable(null)
            navController.navigate(R.id.profileUsers)


        }

         navController.popBackStack()
         navController.navigate(R.id.fragment5)



    }
    ///////// при изменении картинки в профиле , засылаю на свервер и пихаю в базу
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode== Activity.RESULT_OK && data!=null)
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
                                .child(CHIELD_PHOTO_USER_SRC).setValue(photoUrl).addOnCompleteListener {
                                    if(it.isSuccessful)
                                    {
                                        Picasso.get()
                                            .load(photoUrl)
                                            .placeholder(R.drawable.user)   ////// запись в картинку
                                            .into(circleImageViewForActivityMain)

                                    }
                                }

                        }

                    }
                }

            }
        }

    }




}
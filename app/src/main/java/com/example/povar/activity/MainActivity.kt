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
        var name_users= STORAGE.name
        var name =findViewById<TextView>(R.id.textViewNameforActivityMain)
        name.text=name_users
        myRecept
        initFirebase()
        circleImageViewForActivityMain.downloadSetImage(STORAGE.photo)

    }

    override fun onStart() {
        super.onStart()

        var name_users= STORAGE.name
        var name =findViewById<TextView>(R.id.textViewNameforActivityMain)
        name.text=name_users
        var circleImage =findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
       // circleImage.downloadSetImage(STORAGE.photo)
        // получаем navController
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        var navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.popBackStack()
        navController.navigate(R.id.fragment5)

        textViewNameforActivityMain.setOnClickListener{
            name.text=null
            circleImage.setImageDrawable(null)
            navController.navigate(R.id.profileUsers)

        }

            myRecept.setOnClickListener {
                navController.popBackStack()
                navController.navigate(R.id.fragment5)

                }

            AllRecept.setOnClickListener {
                navController.popBackStack()
                navController.navigate(R.id.viewAllRecept)
                }





    }
    ///////// при изменении картинки в профиле , засылаю на свервер и пихаю в базу



}
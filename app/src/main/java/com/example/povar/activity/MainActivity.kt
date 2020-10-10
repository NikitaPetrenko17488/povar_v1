package com.example.povar.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R
import com.example.povar.ui.STORAGE
import com.example.povar.ui.STORAGE_FOR_RECYCLE_RECEPT
import com.example.povar.ui.downloadSetImage
import com.example.povar.ui.initFirebase
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
        initFirebase()
        circleImageViewForActivityMain.downloadSetImage(STORAGE.photo)
        MyReceptClick()
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContext="My"
    }

    override fun onStop() {
        super.onStop()

    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()



        STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain="Main"
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

            navController.navigate(R.id.profileUsers)

        }
            SettingsForActivity.setOnClickListener {
                navController.navigate(R.id.settings)
            }
            myRecept.setOnClickListener {
                MyReceptClick()
                STORAGE_FOR_RECYCLE_RECEPT.fragmentContext="My"
                navController.popBackStack()
                navController.navigate(R.id.fragment5)

                }

            AllRecept.setOnClickListener {
               STORAGE_FOR_RECYCLE_RECEPT.fragmentContext="All"
               AllReceptClick()
                navController.popBackStack()
                navController.navigate(R.id.viewAllRecept)

                }

            dopFunc.setOnClickListener {
                DopClick()
                navController.popBackStack()
                navController.navigate(R.id.kalorii)


            }


    }











   @SuppressLint("ResourceAsColor")
   fun MyReceptClick()
   {
       ViewLineMyRecept.setBackgroundColor(R.color.black)
       ViewLineAllRecept.setBackgroundColor(0x00000000)
       ViewLineDopFun.setBackgroundColor(0x00000000)

       AllRecept.setTypeface(Typeface.DEFAULT)
       myRecept.setTypeface(Typeface.DEFAULT_BOLD);
       dopFunc.setTypeface(Typeface.DEFAULT)
   }

    @SuppressLint("ResourceAsColor")
    fun AllReceptClick()
    {
        AllRecept.setTypeface(Typeface.DEFAULT_BOLD)
        myRecept.setTypeface(Typeface.DEFAULT);
        dopFunc.setTypeface(Typeface.DEFAULT)

        ViewLineMyRecept.setBackgroundColor(0x00000000)
        ViewLineAllRecept.setBackgroundColor(R.color.black)
        ViewLineDopFun.setBackgroundColor(0x00000000)

    }

    @SuppressLint("ResourceAsColor")
    fun DopClick()
    {
        AllRecept.setTypeface(Typeface.DEFAULT)
        myRecept.setTypeface(Typeface.DEFAULT);
        dopFunc.setTypeface(Typeface.DEFAULT_BOLD)

        ViewLineMyRecept.setBackgroundColor(0x00000000)
        ViewLineAllRecept.setBackgroundColor(0x00000000)
        ViewLineDopFun.setBackgroundColor(R.color.black)
    }



}
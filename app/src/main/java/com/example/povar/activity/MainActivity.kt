package com.example.povar.activity

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R
import com.example.povar.fragments.fragment5
import com.example.povar.ui.STORAGE
import com.example.povar.ui.initFirebase
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
        circleImage.setImageDrawable(bd)

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




}
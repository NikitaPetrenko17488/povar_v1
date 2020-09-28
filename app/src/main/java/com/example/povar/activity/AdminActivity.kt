package com.example.povar.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R
import com.example.povar.ui.initFirebase

class AdminActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)


    }

    override fun onStart() {
        super.onStart()
        // получаем navController
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navFragment3) as NavHostFragment? ?: return
        var navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.popBackStack()
        navController.navigate(R.id.viewUsersForAdmin)
    }
}
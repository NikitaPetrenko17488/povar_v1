package com.example.povar.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R
import com.example.povar.ui.STORAGE
import com.example.povar.ui.STORAGE_FOR_RECYCLE_RECEPT
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)


    }

    override fun onStart() {
        super.onStart()
        init()
        STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain="Admin"
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContext="Admin"
        // получаем navController
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navFragment3) as NavHostFragment? ?: return
        var navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = findViewById<Toolbar>(R.id.toolbar3)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.popBackStack()

        push.setOnClickListener {
            if(STORAGE.translater!=1)

            navController.navigate(R.id.pushForAdminRecept)
        }

        if(STORAGE.admin==1)
            navController.navigate(R.id.viewUsersForAdmin)
        if(STORAGE.translater==1)
            navController.navigate(R.id.translater)
    }

    private fun init() {
        if(STORAGE.translater==1)
            push.visibility= View.INVISIBLE
        else
            push.visibility= View.VISIBLE
    }
}
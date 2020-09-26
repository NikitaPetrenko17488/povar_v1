package com.example.povar.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R
import com.example.povar.models.User
import com.example.povar.ui.NODE_USERS
import com.example.povar.ui.REF_DABATABSE_ROOT
import com.example.povar.ui.initFirebase
import com.example.povar.ui.replaceActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class RegistryActivity: AppCompatActivity() {

     var Massiv_Users = mutableListOf<User>()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regisry)
        initFirebase()

    }



    override fun onStart() {
        super.onStart()
        loadDataBase()
        // получаем navController
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navFragment2) as NavHostFragment? ?: return
        var navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar1 = findViewById<Toolbar>(R.id.toolbar1)
        toolbar1.setupWithNavController(navController, appBarConfiguration)
        //toolbar.setBackgroundColor(R.color.colorPrimary)

    }

         fun loadDataBase() {
            REF_DABATABSE_ROOT.child(NODE_USERS)//.child("боржанский")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot: DataSnapshot in dataSnapshot.children) {
                            val user = snapshot.getValue(User::class.java) ?: User()
                            Massiv_Users.add(user)

                        }
                    }
                })


}}

package com.example.povar.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.povar.R
import com.example.povar.activity.AdminActivity
import com.example.povar.activity.MainActivity
import com.example.povar.activity.RegistryActivity
import com.example.povar.models.User
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_edit_code.*


var Massiv_Users = mutableListOf<User>()

open class EditCodeFragment() : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_code, container, false)
    }

    override fun onStart() {
        super.onStart()

        loadDataBase()
        godAutentification()
        startRegistry()
        startOffline()


    }
    private fun loadDataBase() {
        REF_DABATABSE_ROOT.child(NODE_USERS)//.child("боржанский")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "Нет подключения к базе..", Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val user = snapshot.getValue(User::class.java) ?: User()
                        Massiv_Users.add(user)

                    }
                }
            })
    }

    private fun startRegistry()
    {
        StartRegistry.setOnClickListener {  replaceFragment2(EditNumberFragment()) }

    }

    private fun startOffline()
    {
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContext="Offline"
        StartOffline.setOnClickListener {findNavController().navigate(R.id.offline_avtonomnoe) }
    }

//    fun ForTest()
//    {
//
//    }
    private fun godAutentification() {
        button_next1.setOnClickListener {
        var boolLogin: Int = 0 /////////////////////////////////////////////////////////////
        var count = 0

        for (index in Massiv_Users.withIndex()) {
            if (Massiv_Users[count].login==autentificationCodeLogin.text.toString() && Massiv_Users[count].password==autentificationCodePassword.text.toString() )
            {
                boolLogin = 1
            }

            if (boolLogin == 1) {
                break
            }
            count++
        }

        if (boolLogin == 1) {
                STORAGE.ID= Massiv_Users[count].id
                STORAGE.counter =count
                STORAGE.name= Massiv_Users[count].name
                STORAGE.login= Massiv_Users[count].login
                STORAGE.photo= Massiv_Users[count].photoUrl
                STORAGE.counter_recept= Massiv_Users[count].counter_recept
                STORAGE.Tema= Massiv_Users[count].tema

           if (Massiv_Users[count].admin==1)/////////////////////
           {
               STORAGE.admin=1
               startActivity(Intent(activity, AdminActivity::class.java))
           }
            else if (Massiv_Users[count].translater==1)
           {
               STORAGE.translater=1
               startActivity(Intent(activity, AdminActivity::class.java))
           }
            else
            {
                startActivity(Intent(activity, MainActivity::class.java))

            }


        } else {
            showToast("Неправильный логин или пароль")
        }
    }
        Massiv_Users.removeAll{true}
    }


}
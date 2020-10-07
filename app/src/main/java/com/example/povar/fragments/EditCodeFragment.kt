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
        arguments?.let {

        }
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

    fun startRegistry()
    {
        StartRegistry.setOnClickListener {  replaceFragment2(EditNumberFragment()) }

    }

    fun ForTest()
    {

    }
    fun godAutentification() {
        button_next1.setOnClickListener {
        var boolLogin: Int = 0 /////////////////////////////////////////////////////////////
        var count = 0

        for (index in Massiv_Users.withIndex()) {
            if (Massiv_Users[count].login.toString()==autentificationCodeLogin.text.toString() && Massiv_Users[count].password.toString()==autentificationCodePassword.text.toString() )
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

           if (Massiv_Users[count].admin==1)/////////////////////
           {
               startActivity(Intent(activity, AdminActivity::class.java))
           }
            else if (Massiv_Users[count].admin==0)//////////////////
            {
                startActivity(Intent(activity, MainActivity::class.java))

            }


        } else {
            showToast("Неправильный логин или пароль")
        }
    }
        Massiv_Users.removeAll{true}
    }
//                              TEEEEESSSSTTTTTT
//        class MyRegistry:EditCodeFragment(){
//
//            fun Registry(login:String,OgidaemiyLogin:String):String{
//                var auth:Boolean
//                if (login.toString()==OgidaemiyLogin.toString()){
//                   auth=true
//                }
//                else{ Log.d(" Неверный логин","0")
//                auth=false
//                }
//
//                return auth.toString()
//            }
//        }

}
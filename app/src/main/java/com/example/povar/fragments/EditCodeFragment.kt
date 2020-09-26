package com.example.povar.fragments

import android.R.attr.data
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.activity.MainActivity
import com.example.povar.activity.RegistryActivity
import com.example.povar.models.User
import com.example.povar.ui.NODE_USERS
import com.example.povar.ui.REF_DABATABSE_ROOT
import com.example.povar.ui.replaceFragment2
import com.example.povar.ui.showToast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_edit_code.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


var Massiv_Users = mutableListOf<User>()

class EditCodeFragment() : Fragment() {


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
    fun godAutentification() {
        button_next1.setOnClickListener {
        var boolLogin: Int = 0
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

            ////
           // var intent = Intent(RegistryActivity,MainActivity::class.java)
            startActivity(Intent(activity, MainActivity::class.java))
            /////

        } else {
            showToast("Неправильный логин или пароль")
        }
    }
        Massiv_Users.removeAll{true}
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
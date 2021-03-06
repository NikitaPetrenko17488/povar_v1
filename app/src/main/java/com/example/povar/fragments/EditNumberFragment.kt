package com.example.povar.fragments

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
import com.example.povar.models.Calorii
import com.example.povar.models.User
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_edit_number.*





open class EditNumberFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        STORAGE.photo=""
        STORAGE.counter_recept=0

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_edit_number, container, false)
    }

    override fun onStart() {
        super.onStart()
        loadDataBase()

        initfun()

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

    private fun initfun() {
        RegistryEnd.setOnClickListener {
            if (registryCodeLogin.text.toString().isEmpty()) {
                showToast("Введите логин")
            }
            else if  (registryCodePassword.text.toString().isEmpty()) {
                showToast("Введите пароль")
            }
            else if (registryCodeName.text.toString().isEmpty()){
                showToast("Введите имя")
            }
            else
            {
                if(registryCodeLogin.length()<4) { showToast("Логин должен содержать минимум 4 символа")}
                   else if (registryCodePassword.length()<6) {showToast("Пароль должен содержать минимум 6 символов")}
                        else {

                    var boolLogin: Int = 0
                    var count = 0

                    for (index in Massiv_Users.withIndex()) {
                        if (Massiv_Users[count].login.toString() == registryCodeLogin.text.toString()) {
                            boolLogin = 1
                        }
                        count++
                    }
                        if (boolLogin==0) {
                            val login = registryCodeLogin.text.toString()
                            val password = registryCodePassword.text.toString()
                            val name = registryCodeName.text.toString()
                            val dateMap =
                                mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
                            var ID=login
                            dateMap[CHIELD_USER_LOGIN] = login
                            dateMap[CHIELD_USER_PASSWORD] = password
                            dateMap[CHIELD_USER_NAME] = name
                            dateMap[CHIELD_USER_ID]=ID
                            dateMap[CHIELD_USER_TEMA]=false
                            dateMap[CHIELD_USER_LANGUAGE]="Rus"
                            REF_DABATABSE_ROOT.child(NODE_USERS).child(ID)
                                .updateChildren(dateMap)
                                STORAGE.name=name
                                STORAGE.login=login
                                STORAGE.ID=ID
                                

                            startActivity(Intent(activity, MainActivity::class.java))
                        }
                    else {showToast("Такой логин уже существует")}
                }
            }

        }
    }
     val loginTest = listOf(
        User("Admin"),
        User("Admiral"),
        User("admin"))







//    class TEst:EditNumberFragment() {
//        fun test(Edit_login: String): String {
//
//            var boolLogin: Int = 0
//            var count = 0
//            if (Edit_login.length < 4) {
//                showToast("Логин должен содержать минимум 4 символа")   //1
//            } else {
//
//
//                                                                            ///2
//                for (index in loginTest.withIndex()) {          //3
//                    if (loginTest[count].toString() == Edit_login) {                //4
//
//                        boolLogin = 0
//                    }
//                    count++
//                }
//
//            }                                                                   //5
//            return boolLogin.toString()                                             //6
//        }
//
//    }
}
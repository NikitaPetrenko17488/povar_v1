package com.example.povar.activity

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R

import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.example.povar.ui.db.MyDbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

private var MassivOffReload = mutableListOf<Recept>()
var counterOffReload:Int =0

class MainActivity :AppCompatActivity() {

    val myDbManager = MyDbManager(this)

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MassivOffReload.removeAll { true }
        counterOffReload =0

        setContentView(R.layout.activity_main)
        var name_users= STORAGE.name
        var name =findViewById<TextView>(R.id.textViewNameforActivityMain)
        name.text=name_users
        initFirebase()
        circleImageViewForActivityMain.downloadSetImage(STORAGE.photo)
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContext="My"
    }


    override fun onStop() {
        super.onStop()

    }

    override fun onResume() {
        super.onResume()
        MyReceptClick()
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()
        deleteOfflineBase()
        MassivOffReload.removeAll { true }
        counterOffReload =0

        initRecepts()
        TemaPriRefresh()
        MyReceptClick()
        STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain="Main"
        var name_users= STORAGE.name
        var name =findViewById<TextView>(R.id.textViewNameforActivityMain)
        name.text=name_users

        // получаем navController
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        var navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)



       navController.popBackStack()
        navController.navigate(R.id.fragment5)

        circleImageViewForActivityMain.setOnClickListener {
            navController.navigate(R.id.profileUsers)
        }

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


fun TemaPriRefresh(){
    if(STORAGE.Tema==true){
        LinearActivity.setBackgroundResource(R.color.DarkThema)
        LinearActivityBottom.setBackgroundResource(R.color.DarkThema)
        toolbar.setBackgroundResource(R.color.DarkThema)



    }
    else{
        LinearActivity.setBackgroundResource(R.color.LiteThema)
        LinearActivityBottom.setBackgroundResource(R.color.LiteThema)
        toolbar.setBackgroundResource(R.color.LiteThema)

    }

}

    private fun initOfflineBase() {
        myDbManager.insertToDb(
            MassivOffReload[counterOffReload].name,
            MassivOffReload[counterOffReload].ingridients,
            MassivOffReload[counterOffReload].formula)
    }

    fun deleteOfflineBase(){

        myDbManager.openDb()
        myDbManager.deleteDb()

    }

    private fun initRecepts( ) {

        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()

                        MassivOffReload.add(recept)
                        initOfflineBase()
                        counterOffReload++


                    }

                }

            })

    }

}
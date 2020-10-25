package com.example.povar.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.povar.R
import com.example.povar.fragments.offline_avtonomnoe
import com.example.povar.ui.db.MyDbManager

import com.example.povar.ui.initFirebase


class RegistryActivity: AppCompatActivity() {

    val myDbManager = MyDbManager(this)

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regisry)
        initFirebase()



    }

    override fun onStart() {
        super.onStart()
        init()

    }


    fun init(){


        myDbManager.openDb()

//        myDbManager.insertToDb("БЛИНЫ","2 яйца, 0.5 л. молока, 200 г. пшеничной муки, 3 ст. ложки сахара, соль по вкусу, 2 ст. ложки растительного масла.","Взбить яйца до образования белой пены. Добавить сахар и соль, взбить до растворения сахара. Нагреть молоко, чтобы оно было тёпленьким, но не кипяток. Вмешать молоко в яйца, затем вмешать муку. Добавить 2 ст. ложки растительного масла, размешать. Можно жарить (лучше на раскалённой сковороде)! ")
//        myDbManager.insertToDb("Борщ","Мясо, зажарка, соль, вода","Сделать зажарку, вскипятить воду и варить в ней пол часа мясо для нажористости, добавить к мясу и воде зажарку, посолить, готовить до гкйстой консистенции. ")
        val dataList=myDbManager.ReadDb()
        val bundle=Bundle()
        bundle.putSerializable("List",dataList)
         offline_avtonomnoe.getNewInstance(bundle)
        myDbManager.closeDb()


    }


}

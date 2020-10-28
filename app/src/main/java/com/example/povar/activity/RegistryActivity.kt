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

        val dataList=myDbManager.ReadDb()
        val bundle=Bundle()
        bundle.putSerializable("List",dataList)
        offline_avtonomnoe.getNewInstance(bundle)
        myDbManager.closeDb()


    }


}

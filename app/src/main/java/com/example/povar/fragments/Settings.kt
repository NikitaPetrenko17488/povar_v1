package com.example.povar.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.activity.RegistryActivity
import com.example.povar.ui.hideSearch
import com.example.povar.ui.hideSettings
import com.example.povar.ui.showUserNameAdnImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*


class Settings : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)
       hideSettings(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)
        hideSettings(activity!!)

        Exit.setOnClickListener{

            startActivity(Intent(activity, RegistryActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()

    }
}



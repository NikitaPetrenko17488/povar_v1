package com.example.povar.fragments

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.constraintlayout.solver.state.Dimension.Parent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.povar.R
import com.example.povar.ui.STORAGE
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_start.view.*

class fragment1 : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun FragmentActivity.clearFragmentsFromContainer() {
            val fragments = supportFragmentManager.fragments
            for (fragment in fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
            //Remove all the previous fragments in back stack
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

    }


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        //ссылку на фрагмент я беру
        val fragmentLayout = inflater.inflate(R.layout.fragment_start, container, false)
        //навигационный контроллер

        val navController = NavHostFragment.findNavController(this)
        // слушатель кнопки

        fragmentLayout.button2.setOnClickListener{navController.navigate(R.id.fragment2)}
        fragmentLayout.button3.setOnClickListener{navController.navigate(R.id.fragment3)}
        fragmentLayout.button1.setOnClickListener{navController.navigate(R.id.fragment5)}

        return fragmentLayout


    }

    override fun onStart() {
        super.onStart()



    }
}
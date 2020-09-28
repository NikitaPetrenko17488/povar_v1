package com.example.povar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.povar.R
import kotlinx.android.synthetic.main.fragment_start.view.*

class fragment1 : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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
        fragmentLayout.button4.setOnClickListener{navController.navigate(R.id.fragment4)}
        fragmentLayout.button1.setOnClickListener{navController.navigate(R.id.fragment5)}

        return fragmentLayout

    }


}
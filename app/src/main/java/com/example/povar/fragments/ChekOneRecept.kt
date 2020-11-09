package com.example.povar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.fragment_chek_one_recept.*


class ChekOneRecept : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chek_one_recept, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
        spinnerChek.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                val choose = resources.getStringArray(R.array.complexity)
                if(choose[selectedItemPosition]=="Не выбрано")
                    showToast("Выбрите сложность блюда")
                else
                STORAGE_FOR_RECYCLE_RECEPT.complexity=choose[selectedItemPosition]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        })

        CanselChek.setOnClickListener {
            replaceFragment3(PushForAdminRecept())
            showToast("Блюдо не принято..")
        }
        AcceptChek.setOnClickListener {
           updateChek()
        }
    }

    private fun updateChek() {
        val dateMap = mutableMapOf<String, Any>()
        dateMap[CHIELD_RECEPT_CHEK] = true
        dateMap[CHIELD_RECEPT_COMPLEXITY] = STORAGE_FOR_RECYCLE_RECEPT.complexity
        REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID).updateChildren(dateMap)

        showToast("Блюдо принято...")
        replaceFragment3(PushForAdminRecept())
    }

    fun init(){
        EditTextNameChek.setText(STORAGE_FOR_RECYCLE_RECEPT.name)
        EditTextIngridientChek.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridients)
        EditTextFormulaChek.setText(STORAGE_FOR_RECYCLE_RECEPT.formula)

    }


}
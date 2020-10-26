package com.example.povar.fragments

import DataAdapterCalkulatorKalorii
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.CalculatorKalorii
import com.example.povar.ui.*
import com.google.android.gms.actions.ItemListIntents
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calkulator_kalorii.*
import kotlinx.android.synthetic.main.fragment_kalorii.*
import kotlinx.android.synthetic.main.list_calculator_kalorii.*

var MassivCalkulator= mutableListOf<CalculatorKalorii>()



class CalkulatorKalorii : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideUserNameAdnImage(activity!!)
        hideSearch(activity!!)
        hideAddButton(activity!!)
        language()
        //init()
        MassivCalkulator.removeAll { true }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calkulator_kalorii, container, false)
    }

    override fun onStart() {
        super.onStart()
        MassivCalkulator.removeAll { true }
        hideUserNameAdnImage(activity!!)
        hideSearch(activity!!)
        hideAddButton(activity!!)
        language()
        //init()
        createRecycler()
        addSlotCalkulatorKaloriiAbstract.setOnClickListener {

            init()
            createRecycler()
        }


    }


    class ViewHolderCalkulatorCalorii(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_calculator_kalorii, parent, false)) {
        private var mName: TextView? = null
        private var mCalorii: TextView? = null
        private var mGramm: TextView? = null

        init {

            mName = itemView.findViewById(R.id.EditNameIngridients)
            mCalorii = itemView.findViewById(R.id.EditKaloriiGramm)
            mGramm = itemView.findViewById(R.id.EditGramm)


        }

        fun bind(movie: CalculatorKalorii) {

            mName?.text = movie.name
            mCalorii?.text = movie.KaloririiGramm
            mGramm?.text =movie.Gramm

        }
    }

    fun createRecycler() {
        if (activity != null)
            activity!!.rvCalkulatorKalorii.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapterCalkulatorKalorii(MassivCalkulator)

            }

    }


    private fun language() {

        if (STORAGE.Language == "Rus") {
            activity!!.toolbar.setTitle("Калькулятор калорий")
        } else {
            activity!!.toolbar.setTitle("Calorie calculator")
        }
    }

    private fun init() {
        MassivCalkulator.add(
            CalculatorKalorii(EditNameIngridientsAbstract.text.toString(),EditKaloriiGrammAbstract.text.toString(),EditGrammAbstract.text.toString()
        ))
        EditNameIngridientsAbstract.setText("")
        EditKaloriiGrammAbstract.setText("")
        EditGrammAbstract.setText("")

    }
}

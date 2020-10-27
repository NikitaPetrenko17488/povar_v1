package com.example.povar.fragments

import DataAdapterCalkulatorKalorii
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.CalculatorKalorii
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calkulator_kalorii.*
import kotlinx.android.synthetic.main.list_calculator_kalorii.*
import kotlinx.android.synthetic.main.list_calculator_kalorii.view.*
import org.w3c.dom.Text
import kotlin.reflect.typeOf

private var MassivCalkulator= mutableListOf<CalculatorKalorii>()
private var counterCalkulator=0
private var Gramm=0
private var KaloriiGramm=0
private var Res:Float= 0F
private var ResInt=0





class CalkulatorKalorii : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideUserNameAdnImage(activity!!)
        hideSearch(activity!!)
        hideAddButton(activity!!)
       // tema()
        //language()
        //init()
        MassivCalkulator.removeAll { true }
         counterCalkulator=0
         Gramm=0
         KaloriiGramm=0
         Res= 0F
         ResInt=0

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
        tema()
        language()
        //init()
        createRecycler()
        addSlotCalkulatorKaloriiAbstract.setOnClickListener {

            addSlotAndSumm()
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

            /////////TEMA
            if(STORAGE.Tema==true) {
                itemView.TextNameIngridients.setTextColor(Color.parseColor("#b2b2b2"))
                itemView.TextKaloriiGramm.setTextColor(Color.parseColor("#b2b2b2"))
                itemView.TextGramm.setTextColor(Color.parseColor("#b2b2b2"))

                itemView.EditNameIngridients.setTextColor(Color.parseColor("#b2b2b2"))
                itemView.EditKaloriiGramm.setTextColor(Color.parseColor("#b2b2b2"))
                itemView.EditGramm.setTextColor(Color.parseColor("#b2b2b2"))
            }
            else{
                itemView.TextNameIngridients.setTextColor(Color.parseColor("#000000"))
                itemView.TextKaloriiGramm.setTextColor(Color.parseColor("#000000"))
                itemView.TextGramm.setTextColor(Color.parseColor("#000000"))

                itemView.EditNameIngridients.setTextColor(Color.parseColor("#000000"))
                itemView.EditKaloriiGramm.setTextColor(Color.parseColor("#000000"))
                itemView.EditGramm.setTextColor(Color.parseColor("#000000"))
            }
            if(STORAGE.Language=="Rus") {
                itemView.TextNameIngridients.setText("Название ингредиента: ")
                itemView.TextKaloriiGramm.setText("Ккл/100гр: ")
                itemView.TextGramm.setText("Грамм: ")
            }
            else{
                itemView.TextNameIngridients.setText("Ingredient name: ")
                itemView.TextKaloriiGramm.setText("Kcal/100gr: ")
                itemView.TextGramm.setText("Gram: ")
            }

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

        activity!!.toolbar.setTitle("Калькулятор калорий")
        if (STORAGE.Language == "Rus") {
            TextGrammAbstract.setText("Грамм: ")
            TextKaloriiGrammAbstract.setText("Ккл/100гр: ")
            TextNameIngridientsAbstract.setText("Название ингредиента: ")
            TextSumm.setText("Всего: ")

            EditNameIngridientsAbstract.setHint("Название")
            EditKaloriiGrammAbstract.setHint("000 Ккл")
            EditGrammAbstract.setHint("000 грамм")

        } else {
            activity!!.toolbar.setTitle("Calorie calculator")
            TextGrammAbstract.setText("Gram: ")
            TextKaloriiGrammAbstract.setText("Kcal/100gr: ")
            TextNameIngridientsAbstract.setText("Ingredient name: ")
            TextSumm.setText("Total: ")

            EditNameIngridientsAbstract.setHint("Name")
            EditKaloriiGrammAbstract.setHint("000 Kcal")
            EditGrammAbstract.setHint("000 gr")
        }
    }

    private fun addSlotAndSumm() {


            if (EditNameIngridientsAbstract.text.toString().isNotEmpty() && EditKaloriiGrammAbstract.text.toString().isNotEmpty() && EditGrammAbstract.text.toString().isNotEmpty())
            {
                    MassivCalkulator.add(
                        CalculatorKalorii(
                            EditNameIngridientsAbstract.text.toString(),
                            EditKaloriiGrammAbstract.text.toString(),
                            EditGrammAbstract.text.toString()
                        )
                    )
                    EditNameIngridientsAbstract.setText("")
                    EditKaloriiGrammAbstract.setText("")
                    EditGrammAbstract.setText("")

                    Gramm = MassivCalkulator[counterCalkulator].Gramm.toInt()
                    KaloriiGramm = MassivCalkulator[counterCalkulator].KaloririiGramm.toInt()
                    Res += KaloriiGramm.toFloat() * (Gramm.toFloat() / 100)
                    ResInt= (Res*100).toInt()
                    Res= (ResInt.toFloat()/100)
                    Summ.setText(Res.toString())

                    createRecycler()
                    counterCalkulator++
                }
                else{
                    showToast("Заполните все поля")
                }



    }



    private fun tema(){


        EditNameIngridientsAbstract.setHintTextColor(Color.parseColor("#808080"))
        EditKaloriiGrammAbstract.setHintTextColor(Color.parseColor("#808080"))
        EditGrammAbstract.setHintTextColor(Color.parseColor("#808080"))
        if(STORAGE.Tema==true) {
            Constraint_layout_Calkulator.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            TextSumm.setTextColor(Color.parseColor("#b2b2b2"))
             Summ.setTextColor(Color.parseColor("#b2b2b2"))

            TextNameIngridientsAbstract.setTextColor(Color.parseColor("#b2b2b2"))
            TextKaloriiGrammAbstract.setTextColor(Color.parseColor("#b2b2b2"))
            TextGrammAbstract.setTextColor(Color.parseColor("#b2b2b2"))
            EditNameIngridientsAbstract.setTextColor(Color.parseColor("#b2b2b2"))
            EditKaloriiGrammAbstract.setTextColor(Color.parseColor("#b2b2b2"))
            EditGrammAbstract.setTextColor(Color.parseColor("#b2b2b2"))


        }
        else{
            Constraint_layout_Calkulator.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
            TextSumm.setTextColor(Color.parseColor("#000000"))
            Summ.setTextColor(Color.parseColor("#000000"))

            TextNameIngridientsAbstract.setTextColor(Color.parseColor("#000000"))
            TextKaloriiGrammAbstract.setTextColor(Color.parseColor("#000000"))
            TextGrammAbstract.setTextColor(Color.parseColor("#000000"))
            EditNameIngridientsAbstract.setTextColor(Color.parseColor("#000000"))
            EditKaloriiGrammAbstract.setTextColor(Color.parseColor("#000000"))
            EditGrammAbstract.setTextColor(Color.parseColor("#000000"))
            EditNameIngridientsAbstract.setHintTextColor(Color.parseColor("#C0C0C0"))

        }

    }
}

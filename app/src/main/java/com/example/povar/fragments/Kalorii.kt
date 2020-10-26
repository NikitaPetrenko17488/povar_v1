package com.example.povar.fragments

import DataAdapterCalorii
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.Calorii
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_kalorii.*

private val MassivCalorii = listOf(
    Calorii("белый гриб", "White mushroom","34"),
    Calorii("бекон", "Bacon","500"),
    Calorii("говядина", "Beef","250"),
    Calorii("горох", "Peas","320"),
    Calorii("индейка","Turkey","189"),
    Calorii("кукурузная крупа", "Corn grits","320"),
    Calorii("курица", "Chiken","214"),
    Calorii("колбаса варенная", "Boiled sausage","223"),
    Calorii("колбаса сырокопченая", "Raw smoked sausage","450"),
    Calorii("каша гречневая", "Buckwheat porridge","120"),
    Calorii("лук","Bow" ,"40"),
    Calorii("манная крупа","Semolina", "333"),
    Calorii("мюсли", "Muesli","400"),
    Calorii("морковь", "Carrot","35"),
    Calorii("огурец", "Cucumber","16"),
    Calorii("помидор","Tomato", "18"),
    Calorii("перец", "Pepper","40"),
    Calorii("свинина", "Pork","144"),
    Calorii("фасоль", "Beans","350"),
    Calorii("ячменная каша","Barley porridge", "400")

    )
class Kalorii : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        showSettings(activity!!)
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kalorii, container, false)
    }


    override fun onStart() {
        super.onStart()
        init()
        tema()
        language()
        showSettings(activity!!)
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)
        activity!!.addRecept.setOnClickListener {
            findNavController().navigate(R.id.calkulatorKalorii)
        }



        create_recycle()
    }

    override fun onStop() {
        super.onStop()
            //showSearchForSettings(activity!!)
    }

    class MovieViewHolderCalorii(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_kalorii, parent, false)) {
        private var mName: TextView? = null
        private var mCalorii: TextView? = null
        private var mKkl:TextView?=null

        init {

            mName = itemView.findViewById(R.id.NazvanieKalorii)
            mCalorii = itemView.findViewById(R.id.KaloriiKalorii)
            mKkl=itemView.findViewById(R.id.kkl100gr)



        }
        fun bind(movie: Calorii) {


            if(STORAGE.Tema==true){

                mName?.setTextColor(Color.parseColor("#b2b2b2"))
                mCalorii?.setTextColor(Color.parseColor("#b2b2b2"))
                mKkl?.setTextColor(Color.parseColor("#b2b2b2"))


            }
            else
            {
                mName?.setTextColor(Color.parseColor("#000000"))
                mCalorii?.setTextColor(Color.parseColor("#000000"))
                mKkl?.setTextColor(Color.parseColor("#000000"))


            }

            mCalorii?.text = movie.kalorii
            if(STORAGE.Language=="Rus") {
                mName?.text = movie.name
                mKkl?.text = "ккал/100гр"
            }
            else {
                mName?.text = movie.nameEng
                mKkl?.text = "kcal/100gr"
            }

        }


    }


    fun create_recycle() {

        recyclerCalorii.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DataAdapterCalorii(MassivCalorii as MutableList<Calorii>)

        }

    }


    private fun init(){
        showAddButton(activity!!)

    }
    private fun tema(){

        if(STORAGE.Tema==true)
        {
            activity!!.addRecept.setBackgroundResource(R.drawable.kalkulator_dark)
            Constraint_layout_calorii.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
        }
        else
        {
            activity!!.addRecept.setBackgroundResource(R.drawable.kalkulator)
            Constraint_layout_calorii.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
        }
    }
    private fun language(){
        if(STORAGE.Language=="Eng")
            activity!!.toolbar.setTitle("Calories")
        else
            activity!!.toolbar.setTitle("Калории")
    }

}
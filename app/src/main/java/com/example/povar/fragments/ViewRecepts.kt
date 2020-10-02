package com.example.povar.fragments

import DataAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R

import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_view1.*

interface Click
{
    fun sendData()
}


private var Massiv = mutableListOf<Recept>()
    var counter =0
    var wait:Int=0
     var mButton: Button? = null



class fragment5 : Fragment(),Click {

    var xuy: Click? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Massiv.removeAll { true }
        counter=0
        wait=0



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_view1, container,false)

    }

    override fun onStart() {
        super.onStart()


         initRecepts()


    }


    override fun onPause() {
        super.onPause()
        Massiv.removeAll { true }
        counter=0


    }

     class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_recept, parent, false)) {
        private var mName: TextView? = null
        private var mFormula: TextView? = null

        init {

            mName = itemView.findViewById(R.id.name_recept)
            mFormula = itemView.findViewById(R.id.formula_recept)
            mButton = itemView.findViewById(R.id.editReceptItem);

        }
        fun bind(movie: Recept) {

            mName?.text = movie.name
            mFormula?.text = movie.ingridients

            mButton?.setOnClickListener{


                STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
                STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
                STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
                STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoSrc
                STORAGE_FOR_RECYCLE_RECEPT.update=1


            }
        }


    }


   fun create_recycle() {

                    recicle_view_recept.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = DataAdapter(Massiv,this@fragment5)

                    }

   }


    private fun initRecepts( ) {

        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()
                        Massiv.add(recept)
                        counter++

                        if (counter>0)
                            create_recycle()
                    }

                }

            })


    }




    override fun sendData() {
        Log.d("z", "z")
       replaceFragment(fragment3())
    }


}
package com.example.povar.fragments

import DataAdapter
import DataAdapterAll
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_view1.*
import kotlinx.android.synthetic.main.fragment_view_all_recept.*

private var Massiv = mutableListOf<Recept>()
var counterAll =0

interface ClickAll
{
    fun viewRecycle()
}

class ViewAllRecept : Fragment(),ClickAll {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Massiv.removeAll { true }
        counterAll=0
    }

    override fun onPause() {
        super.onPause()
        Massiv.removeAll { true }
        counterAll=0
    }

    override fun onStart() {
        super.onStart()
        initRecepts()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_all_recept, container, false)
    }



    fun create_recycleAll() {

        recicle_view_receptAll.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DataAdapterAll(Massiv,this@ViewAllRecept)

        }

    }


    class MovieViewHolderAll(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_recept_all, parent, false)) {
        private var mName: TextView? = null
        private var mFormula: TextView? = null
        private var mPhoto: ImageView? = null

        init {

            mName = itemView.findViewById(R.id.name_recept_All)
            mFormula = itemView.findViewById(R.id.formula_recept_All)
            mPhoto=itemView.findViewById(R.id.image_src_All)

        }
        fun bind(movie: Recept) {

            mName?.text = movie.name
            mFormula?.text = movie.ingridients
            mPhoto?.downloadSetImage(movie.photoUrl)

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

                        counterAll++

                        if (counterAll>0)
                            create_recycleAll()
                    }

                }

            })


    }

    override fun viewRecycle() {

        findNavController().navigate(R.id.viewOneReceptInRecycle)
       // replaceFragment(ViewOneReceptInRecycle())

        hideUserNameAdnImage(activity!!)
    }

}
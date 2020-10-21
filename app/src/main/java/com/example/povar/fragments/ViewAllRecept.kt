package com.example.povar.fragments

import DataAdapterAll
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.activity.MainActivity
import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
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

        showSettings(activity!!)
        showSearch(activity!!)
        showUserNameAdnImage(activity!!)
        hideAddButton(activity!!)

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

        showSettings(activity!!)
        showSearch(activity!!)
        showUserNameAdnImage(activity!!)
        hideAddButton(activity!!)

        if(STORAGE.Tema==true)
        {
            Constraint_layout_all_view.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
        }
        else
        {
            Constraint_layout_all_view.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
        }
        initRecepts()
        activity!!.SearchReceptButton.setOnClickListener { SearchAllRecept() }

        RefreshAllRecept.setOnRefreshListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }
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
            if(recicle_view_receptAll!=null) {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapterAll(Massiv, this@ViewAllRecept)
            }
        }

    }


    class MovieViewHolderAll(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_recept_all, parent, false)) {
        private var mName: TextView? = null
        private var mConstraint: ConstraintLayout? = null
        private var mPhoto: ImageView? = null


        init {
            mConstraint=itemView.findViewById(R.id.Constraint_layout_all)
            mName = itemView.findViewById(R.id.name_recept_All)
            mPhoto=itemView.findViewById(R.id.image_src_All)


        }
        fun bind(movie: Recept) {

            if(STORAGE.Tema==true)
            { mName?.setTextColor(Color.parseColor("#b2b2b2"))

            }
            else
            { mName?.setTextColor(Color.parseColor("#000000"))
                }


            mName?.text = movie.name

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

    }

   private fun SearchAllRecept() {
        if (STORAGE_FOR_RECYCLE_RECEPT.fragmentContext == "All") {
            counter = 0
            Massiv.removeAll { true }

            REF_DABATABSE_ROOT.child(NODE_RECEPTS)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        showToast("Нет подключения к базе..")
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot: DataSnapshot in dataSnapshot.children) {
                            val recept = snapshot.getValue(Recept::class.java) ?: Recept()

                            var vxodStroki: String
                            var ReceptIngridientsUp=recept.ingridients.toUpperCase()
                            vxodStroki = activity!!.SearchRecept.text.toString().toUpperCase()
                            var indexIngridient: Boolean = ReceptIngridientsUp.contains(vxodStroki)

                            if (indexIngridient == true) {
                                Massiv.add(recept)
                            } else if (vxodStroki.isEmpty()) {

                                    Massiv.add(recept)

                            }


                            counter++

                            if (counter > 0)
                                create_recycleAll()
                        }

                    }

                })
        }
    }

}
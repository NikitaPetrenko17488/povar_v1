package com.example.povar.fragments

import DataAdapter
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R

import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile_users.*
import kotlinx.android.synthetic.main.fragment_view1.*

interface Click
{
    fun updateRecycle()

    fun deletteRecycle()

    fun viewRecycle()

}


private var Massiv = mutableListOf<Recept>()
    var counter:Int =0



class fragment5 : Fragment(),Click {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Massiv.removeAll { true }
        counter=0


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


        addMyRecept.setOnClickListener{replaceFragment(fragment2())}

    }

    override fun onStop() {
        super.onStop()

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
         private var mPhoto: ImageView? = null

        init {

            mName = itemView.findViewById(R.id.name_recept)
            mFormula = itemView.findViewById(R.id.formula_recept)
            mPhoto=itemView.findViewById(R.id.image_src)

        }
        fun bind(movie: Recept) {

            mName?.text = movie.name
            mFormula?.text = movie.ingridients
            mPhoto?.downloadSetImage(movie.photoUrl)

        }


    }


   fun create_recycle() {

       if (Massiv.isEmpty()){
           ToastNoRecepts.text=" Нет рецептов "
       }
       else
       {
           ToastNoRecepts.text=""
       }
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
                        if(recept.user_id==STORAGE.ID) {
                            Massiv.add(recept)
                        }
                        counter++

                        if (counter>0)
                            create_recycle()
                    }

                }

            })


    }




    override fun updateRecycle() {
       replaceFragment(fragment3())

        hideUserNameAdnImage(activity!!)

    }

    override fun deletteRecycle() {
        REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID)
            .removeValue { error, ref ->  }
        replaceFragment(fragment5())
    }

    override fun viewRecycle() {
        findNavController().navigate(R.id.viewOneReceptInRecycle)
      //replaceFragment(ViewOneReceptInRecycle())

        hideUserNameAdnImage(activity!!)
    }




}
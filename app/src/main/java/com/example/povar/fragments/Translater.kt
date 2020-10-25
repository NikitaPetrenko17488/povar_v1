package com.example.povar.fragments

import DataAdapterTranslate
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.activity.RegistryActivity
import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_translater.*
import kotlinx.android.synthetic.main.fragment_view1.*
import kotlinx.android.synthetic.main.view_users_for_admin.*

private var MassivReceptTranslate = mutableListOf<Recept>()
var counterTranslate:Int =0

interface ClickTranslate
{
    fun TranslateRecycle()

}

class Translater : Fragment(),ClickTranslate {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MassivReceptTranslate.removeAll { true }
        counterTranslate=0

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translater, container, false)
    }

    override fun onStart() {
        super.onStart()
        MassivReceptTranslate.removeAll { true }
        counterTranslate=0


        ExitForTranslater.setOnClickListener {
             startActivity(Intent(activity, RegistryActivity::class.java))
            STORAGE.translater=0
        }

        initRecepts()




    }



    fun create_recycleTranslate() {



        if (MassivReceptTranslate.isEmpty()) {
            if(activity!=null)
                activity!!.ToastNoTranslate.text = " Пока что нечего переводить "
        } else {
            if(activity!=null)
                activity!!.ToastNoTranslate.text = " "
        }
        if (recicle_view_recept_for_translate != null) {
            activity!!.recicle_view_recept_for_translate.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapterTranslate(MassivReceptTranslate, this@Translater)

            }
        }



    }


    private fun initRecepts( ) {

        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    MassivReceptTranslate.removeAll { true }
                    counterTranslate=0
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()
                        if(recept.formula_eng.isEmpty()) {
                            MassivReceptTranslate.add(recept)
                        }
                        counterTranslate++

                        if (counterTranslate>0)
                            create_recycleTranslate()
                    }

                }

            })

    }
    class TranslateReceptHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_recept_translate, parent, false)) {
        private var mName: TextView? = null
        private var mPhoto: ImageView? = null
        private var mTraslate: ImageView?=null

        init {

            mName = itemView.findViewById(R.id.name_recept_translate)
            mPhoto=itemView.findViewById(R.id.image_src_translate)

        }
        @SuppressLint("ResourceAsColor")
        fun bind(movie: Recept) {


            mName?.text = movie.name
            mPhoto?.downloadSetImage(movie.photoUrl)
        }


    }

    override fun TranslateRecycle() {

        findNavController().navigate(R.id.translateRecept)
    }
}
package com.example.povar.fragments

import DataAdapterOffline
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.example.povar.ui.db.MyDbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_offline_avtonomnoe.*

var MassivOffline2=ArrayList<Recept>()
private var MassivOffline = mutableListOf<Recept>()
var MassivOfflineSearch= mutableListOf<Recept>()
var counterOffline =0


interface ClickOffline
{
    fun ViewRecept()

}

class offline_avtonomnoe : Fragment(),ClickOffline {



    companion object{
        fun getNewInstance(args:Bundle):offline_avtonomnoe{
            val offlineFragment=offline_avtonomnoe()
            offlineFragment.arguments=args
            offline_avtonomnoe().arg(offlineFragment.arguments!!)
            return offlineFragment

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        MassivOffline.removeAll { true }
        counterOffline=0


    }

    override fun onStop() {
        super.onStop()

        MassivOffline.removeAll { true }
        counterOffline=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offline_avtonomnoe, container, false)
    }

    override fun onStart() {
        super.onStart()

        initRecepts()
        create_recycleOffline2()
        ExitInOffline.setOnClickListener { findNavController().navigate(R.id.editCodeFragment) }
        SearchReceptOfflineButton.setOnClickListener { search()  }


    }



    override fun onDestroy() {
        super.onDestroy()

    }

    fun create_recycleOffline() {
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContextTwo="Internet"
            rvAllOffline.apply {
                if (rvAllOffline != null) {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = DataAdapterOffline(MassivOffline, this@offline_avtonomnoe)
                }
            }
    }

    fun create_recycleOffline2(){
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContextTwo="NoInternet"
        rvAllOffline.apply {
            if (rvAllOffline != null) {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapterOffline(MassivOffline2, this@offline_avtonomnoe)
            }
        }
    }
    fun create_recycleOfflineSearch(){
        STORAGE_FOR_RECYCLE_RECEPT.fragmentContextTwo="NoInternet"
        rvAllOffline.apply {
            if (rvAllOffline != null) {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapterOffline(MassivOfflineSearch, this@offline_avtonomnoe)
            }
        }
    }



    override fun ViewRecept() {

    findNavController().navigate(R.id.viewOneReceptInRecycle)

    }

   fun arg(arg:Bundle){

       val kek: ArrayList<Recept> = arg["List"] as ArrayList<Recept>
       //Log.d("xuy",kek.toString())

       MassivOffline2=kek



   }

    class MovieViewHolderOffline(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_recept_all, parent, false)) {
        private var mName: TextView? = null
        private var mConstraint: ConstraintLayout? = null
        private var mPhoto: ImageView? = null


        init {
            mConstraint = itemView.findViewById(R.id.Constraint_layout_all)
            mName = itemView.findViewById(R.id.name_recept_All)
            mPhoto = itemView.findViewById(R.id.image_src_All)


        }

        fun bind(movie: Recept) {


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

                        MassivOffline.add(recept)
                        counterOffline++

                            create_recycleOffline()


                    }

                }

            })


    }

   @SuppressLint("DefaultLocale")
   private fun search() {


       if (MassivOffline.isNotEmpty())
       {
           MassivOffline.removeAll { true }
           counterOffline = 0

           REF_DABATABSE_ROOT.child(NODE_RECEPTS)
               .addListenerForSingleValueEvent(object : ValueEventListener {
                   override fun onCancelled(error: DatabaseError) {
                       showToast("Нет подключения к базе..")
                   }

                   override fun onDataChange(dataSnapshot: DataSnapshot) {
                       for (snapshot: DataSnapshot in dataSnapshot.children) {
                           val recept = snapshot.getValue(Recept::class.java) ?: Recept()

                           var receptIngridientsUp = recept.ingridients.toUpperCase()
                           var vxodStroki= activity!!.SearchOffline.text.toString().toUpperCase()

                           var indexIngridient: Boolean = receptIngridientsUp.contains(vxodStroki)


                           if (indexIngridient == true) {
                               MassivOffline.add(recept)

                           }
                           counter++

                           if (counter > 0)
                               create_recycleOffline()

                       }

                   }

               })
       }
       else
       {
           MassivOfflineSearch.removeAll { true }
         var vxodStroki=activity!!.SearchOffline.text.toString().toUpperCase()



           for ((index, element) in MassivOffline2.withIndex()){
               var endMassivOffline= MassivOffline2[index].ingridients.toUpperCase()
              if(endMassivOffline.contains(vxodStroki))
              {
                  MassivOfflineSearch.add(Recept("", MassivOffline2[index].name,MassivOffline2[index].ingridients,
                      MassivOffline2[index].formula,"","",""))

              }
           }
           create_recycleOfflineSearch()

       }
   }



}
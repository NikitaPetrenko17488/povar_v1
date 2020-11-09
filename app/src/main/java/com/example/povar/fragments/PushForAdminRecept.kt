package com.example.povar.fragments

import DataAdapter
import DataAdapterChek
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
import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.fragment_push_for_admin_recept.*
import kotlinx.android.synthetic.main.fragment_view1.*

interface ClickChek
{
    fun viewRecycle()
}


class PushForAdminRecept : Fragment(),ClickChek {



    private var MassivChek = mutableListOf<Recept>()
    var counterChek:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_push_for_admin_recept, container, false)
    }

    override fun onStart() {
        super.onStart()
        initRecepts()
        if(activity!=null)
        activity!!.push.visibility=View.INVISIBLE
    }


    private fun initRecepts( ) {


        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    MassivChek.removeAll { true }
                    counterChek=0
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()
                        if(recept.chek==false)
                                MassivChek.add(recept)

                        counterChek++

                        if (counterChek > 0)
                            create_recycle()
                    }

                }

            })

    }


    fun create_recycle() {

        if (rvChek != null) {
            activity!!.rvChek.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapterChek(MassivChek, this@PushForAdminRecept)
            }
        }
    }

    class MovieViewHolderChek(inflater: LayoutInflater, parent: ViewGroup) :
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
                mName?.text = movie.name
                mPhoto?.downloadSetImage(movie.photoUrl)
        }
    }

    override fun viewRecycle() {

        replaceFragment3(ChekOneRecept())
    }

}
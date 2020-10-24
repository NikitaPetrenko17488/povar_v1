package com.example.povar.fragments

import DataAdapterReceptsUserForAdmin
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_view_profile_users_for_admin.*

interface ReceptsUserForAdmin
{
    fun ViewReceptsUserForAdmin()
    fun EditReceptsUserForAdmin()
    fun DeletteReceptsUserForAdmin()


}

private var MassivReceptUserForAdmin = mutableListOf<Recept>()
private var counterReceptUserForAdmin:Int =0

class ViewProfileUsersForAdmin : Fragment(),ReceptsUserForAdmin {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MassivReceptUserForAdmin.removeAll { true }
        counterReceptUserForAdmin=0

    }

    override fun onStop() {
        super.onStop()
        MassivReceptUserForAdmin.removeAll { true }
        counterReceptUserForAdmin=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_profile_users_for_admin, container, false)
    }


    override fun onStart() {
        super.onStart()
        initProfile()
        initBase()
    }


    fun initProfile()
    {
        Log.d("xuy",STORAGE_USERS_FOR_ADMIN.photo)
        ImageInProfileForAdmin.downloadSetImage(STORAGE_USERS_FOR_ADMIN.photo)
        NameInProfileForAdmim.text=STORAGE_USERS_FOR_ADMIN.name
        LoginInProfileForAdmim.text=STORAGE_USERS_FOR_ADMIN.login
        PasswordInProfileForAdmim.text=STORAGE_USERS_FOR_ADMIN.password
        CounterReceptInProfileForAdmim.text=STORAGE_USERS_FOR_ADMIN.counter_recept.toString()

    }

    class MovieViewHolderReceptsUserForAdmin(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_recept, parent, false)) {

        private var mName: TextView? = null
        private var mPhoto: ImageView?= null

        init {
            mName = itemView.findViewById(R.id.name_recept)
            mPhoto=itemView.findViewById(R.id.image_src)

        }
        fun bind(movie: Recept) {
            mName?.text = movie.name
            mPhoto?.downloadSetImage(movie.photoUrl)


        }
    }

    fun create_recycle() {

        if (MassivReceptUserForAdmin.isEmpty()){
            if (activity!=null) {
                activity!!.ToastNoReceptsUserForAdmin.text = " Нет рецептов "
            }
        }
        else {
            if(activity!=null) {
            activity!!.ToastNoReceptsUserForAdmin.text = " "
            }
        }
        if(rvReceptsUserForAdmin!=null){
            rvReceptsUserForAdmin.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapterReceptsUserForAdmin(
                    MassivReceptUserForAdmin,
                    this@ViewProfileUsersForAdmin
                )
            }

        }

    }

    fun initBase()
    {
        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()
                        if(recept.user_id== STORAGE_USERS_FOR_ADMIN.ID) {
                            MassivReceptUserForAdmin.add(recept)
                        }
                        counterReceptUserForAdmin++

                        if (counterReceptUserForAdmin>=0)
                            create_recycle()
                    }

                }

            })


    }

    override fun ViewReceptsUserForAdmin() {
        findNavController().navigate(R.id.viewOneReceptInRecycle)
    }

    override fun EditReceptsUserForAdmin() {
        findNavController().navigate(R.id.fragment3)
    }

    override fun DeletteReceptsUserForAdmin() {

        val builder= AlertDialog.Builder(activity)
        builder.setTitle("Удалить рецепт?")

        builder.setNeutralButton("Отмена"){dialogOtmena, i ->

            showToast("Отмена")
        }
        builder.setNegativeButton("Удалить"){dialogDelete, i ->

            REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID)
                .removeValue { error, ref ->  }

            val dateMapUser =
                mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
            STORAGE_USERS_FOR_ADMIN.counter_recept=STORAGE_USERS_FOR_ADMIN.counter_recept-1
            dateMapUser[CHIELD_COUNTER_RECEPT]=STORAGE_USERS_FOR_ADMIN.counter_recept

            REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE_USERS_FOR_ADMIN.ID).updateChildren(dateMapUser)
            replaceFragment3(ViewProfileUsersForAdmin())
            MassivReceptUserForAdmin.removeAll { true }
            counterReceptUserForAdmin=0
        }
        builder.show()


    }


}
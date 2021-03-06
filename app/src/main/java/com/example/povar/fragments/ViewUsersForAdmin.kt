package com.example.povar.fragments

import DataAdapterUsers
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
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
import com.example.povar.activity.RegistryActivity
import com.example.povar.models.Recept
import com.example.povar.models.User
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.dialog_alert_delete_dark.view.*
import kotlinx.android.synthetic.main.fragment_view1.*
import kotlinx.android.synthetic.main.view_users_for_admin.*

interface ViewAdmin
{
    fun ViewUser()

    fun DeletteUser()


}

private var Massiv_Users2 = mutableListOf<User>()
private var Massiv_Recepts = mutableListOf<Recept>()
var counter2 =0

class ViewUsersForAdmin : Fragment(),ViewAdmin {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Massiv_Users2.removeAll { true }
        counter2=0
    }

    override fun onStop() {
        super.onStop()
        Massiv_Users2.removeAll { true }
        counter2=0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_users_for_admin, container, false)
    }


    override fun onStart() {
        super.onStart()
        if(activity!=null)
        activity!!.push.visibility=View.VISIBLE
        initUsers()
        initRecepts()
        ExitForAdmin.setOnClickListener {
            STORAGE.admin=0
            startActivity(Intent(activity, RegistryActivity::class.java)) }
    }



    fun create_recycle() {
            if(recicle_view_users!=null){
        recicle_view_users.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DataAdapterUsers(Massiv_Users2, this@ViewUsersForAdmin)
        }
    }


    }
    class MovieViewHolder2(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_users, parent, false)) {
        //private var mName: TextView? = null
        private var mLogin: TextView? = null
        private var mPhoto: CircleImageView?= null

        init {
            //mName = itemView.findViewById(R.id.textViewNameForAdmin)
            mLogin = itemView.findViewById(R.id.textViewLoginForAdmin)
            mPhoto=itemView.findViewById(R.id.imageViewUsersForAdmin)

        }
        fun bind(movie: User) {
           // mName?.text = movie.name
            mLogin?.text = movie.login
            mPhoto?.downloadSetImage(movie.photoUrl)


        }
    }

    private fun initUsers() {

        REF_DABATABSE_ROOT.child(NODE_USERS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Massiv_Users2.removeAll { true }
                    counter2=0
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val user = snapshot.getValue(User::class.java) ?: User()

                            Massiv_Users2.add(user)

                        counter2++


                        if (counter2>0)
                            create_recycle()
                    }

                }

            })


    }

    private fun initRecepts() {


        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Massiv_Recepts.removeAll { true }
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()
                        if(recept.chek==false)
                        Massiv_Recepts.add(recept)

                        if(Massiv_Recepts.isNotEmpty()) {
                            if (activity != null)
                                activity!!.push.setBackgroundResource(R.drawable.push_active)
                            break
                        }
                        else
                        {
                            if (activity != null)
                                activity!!.push.setBackgroundResource(R.drawable.push)
                        }

                    }

                }

            })
    }

    override fun ViewUser() {


        findNavController().navigate(R.id.viewProfileUsersForAdmin)

    }

    override fun DeletteUser() {


        val builder=AlertDialog.Builder(activity)
           builder.setTitle("Удалить пользователя?")

        builder.setNeutralButton("Отмена"){dialogOtmena, i ->

            showToast("Отмена")
        }
        builder.setNegativeButton("Удалить"){dialogDelete, i ->

            REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE_USERS_FOR_ADMIN.ID)
                .removeValue { error, ref ->  }
            Massiv_Users2.removeAll { true }
            counter2=0

                initUsers()
        }
        builder.show()


    }


    

}
package com.example.povar.fragments

import DataAdapterUsers
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.User
import com.example.povar.ui.NODE_USERS
import com.example.povar.ui.REF_DABATABSE_ROOT
import com.example.povar.ui.showToast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_view1.*
import kotlinx.android.synthetic.main.view_users_for_admin.*


private var Massiv_Users2 = mutableListOf<User>()
var counter2 =0

class ViewUsersForAdmin : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        initUsers()
    }

    fun create_recycle() {

        Log.d("ploxo","ploxo")
        recicle_view_users.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DataAdapterUsers(Massiv_Users2)

        }

    }
    class MovieViewHolder2(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_users, parent, false)) {
        private var mName: TextView? = null
        private var mLogin: TextView? = null

        init {
            mName = itemView.findViewById(R.id.textViewNameForAdmin)
            mLogin = itemView.findViewById(R.id.textViewLoginForAdmin)

        }
        fun bind(movie: User) {
            mName?.text = movie.name
            mLogin?.text = movie.login

        }
    }

    private fun initUsers() {

        REF_DABATABSE_ROOT.child(NODE_USERS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val user = snapshot.getValue(User::class.java) ?: User()
                        Massiv_Users2.add(user)
                        Log.d("ploxo2", Massiv_Users2[counter2].name)
                        counter2++


                        if (counter2>0)
                            create_recycle()
                    }

                }

            })


    }

}
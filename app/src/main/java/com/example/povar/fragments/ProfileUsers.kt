package com.example.povar.fragments

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.activity.RegistryActivity
import com.example.povar.ui.STORAGE
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile_users.*


class ProfileUsers : Fragment() {

    var name =""
    var login=""
    var photo=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile_users, container, false)
    }


    override fun onStart() {
        super.onStart()
        name=STORAGE.name
        login=STORAGE.login
        photo=STORAGE.photo


        var name_user =
            activity!!.findViewById<View>(R.id.NameInProfile) as TextView

        var login_user =
            activity!!.findViewById<TextView>(R.id.LoginInProfile)
        name_user.text=name
        login_user.text=login

        Exit.setOnClickListener{

            startActivity(Intent(activity, RegistryActivity::class.java))

        }

    }

    override fun onStop() {
        val bd: BitmapDrawable = activity!!.resources.getDrawable(R.drawable.user) as BitmapDrawable
        super.onStop()
        name=STORAGE.name
        var nameUserInActivity =
            activity!!.findViewById<TextView>(R.id.textViewNameforActivityMain)
        nameUserInActivity.text=name
        var circleImage =
            activity!!.findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
        circleImage.setImageDrawable(bd)


    }
}
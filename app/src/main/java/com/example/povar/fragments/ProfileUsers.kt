package com.example.povar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.fragment_profile_users.*


class ProfileUsers : Fragment() {

    var name =""
    var login=""
    var photo=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name=STORAGE.name
        login=STORAGE.login
        photo=STORAGE.photo

        showSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile_users, container, false)
    }


    override fun onStart() {
        super.onStart()

        showSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)

        name=STORAGE.name
        login=STORAGE.login
        photo=STORAGE.photo


        EditProfile.setOnClickListener{
            hideUserNameAdnImage(activity!!)
            replaceFragment(EditProfileUser())

        }

        var name_user =
            activity!!.findViewById<View>(R.id.NameInProfile) as TextView

        var login_user =
            activity!!.findViewById<TextView>(R.id.LoginInProfile)
        var counter_recept =
            activity!!.findViewById<TextView>(R.id.count_recept_for_user_4islo)

        ImageViewInProfile.downloadSetImage(STORAGE.photo)


        name_user.text=name
        login_user.text=login
        if(STORAGE.counter_recept.toString().isNotEmpty()){counter_recept.text=STORAGE.counter_recept.toString()}

    }

    override fun onStop() {
        super.onStop()


    }
}
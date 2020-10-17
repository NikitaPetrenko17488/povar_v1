package com.example.povar.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.ui.*
import kotlinx.android.synthetic.main.fragment_profile_users.*
import kotlinx.android.synthetic.main.fragment_view_one_recept_in_recycle.*


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
        hideAddButton(activity!!)
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
        hideAddButton(activity!!)
        tema()

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

    private fun tema() {
        if (STORAGE.Tema == true) {
            Constraint_layout_profile_users.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
            NameTextInProfie.setTextColor(Color.parseColor("#b2b2b2"))
            NameInProfile.setTextColor(Color.parseColor("#b2b2b2"))
            LoginTextInProfie.setTextColor(Color.parseColor("#b2b2b2"))
            LoginInProfile.setTextColor(Color.parseColor("#b2b2b2"))
            count_recept_for_user.setTextColor(Color.parseColor("#b2b2b2"))
            count_recept_for_user_4islo.setTextColor(Color.parseColor("#b2b2b2"))
            EditProfile.background=null
            EditProfile.setTextColor(Color.parseColor("#b2b2b2"))


        } else {
            Constraint_layout_profile_users.setBackgroundResource(R.drawable.fon_na_fragment)
            NameTextInProfie.setTextColor(Color.parseColor("#000000"))
            NameInProfile.setTextColor(Color.parseColor("#000000"))
            LoginTextInProfie.setTextColor(Color.parseColor("#000000"))
            LoginInProfile.setTextColor(Color.parseColor("#000000"))
            count_recept_for_user.setTextColor(Color.parseColor("#000000"))
            count_recept_for_user_4islo.setTextColor(Color.parseColor("#000000"))
            EditProfile.setBackgroundResource(R.drawable.megaoval)
            EditProfile.setTextColor(Color.parseColor("#000000"))
        }
    }
}
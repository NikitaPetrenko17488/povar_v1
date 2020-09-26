package com.example.povar.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.povar.R
import com.example.povar.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

fun Fragment.showToast(message:String)
{
Toast.makeText(this.context,message,Toast.LENGTH_SHORT).show()

}

fun AppCompatActivity.replaceActivity(activity:AppCompatActivity){

    val intent = Intent(this,activity::class.java)
    startActivity(intent)
    this.finish()
}


fun Fragment.replaceFragment(fragment: Fragment)
{
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.navFragment,
            fragment
        )?.commit()

}
fun Fragment.replaceFragment2(fragment: Fragment)
{
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.navFragment2,
            fragment
        )?.commit()

}

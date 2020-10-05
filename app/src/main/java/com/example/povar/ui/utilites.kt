package com.example.povar.ui

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.povar.R
import com.example.povar.models.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*

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


fun hideUserNameAdnImage( activity:Activity)
{
    var nameUserInActivity =
        activity.findViewById<TextView>(R.id.textViewNameforActivityMain)
    nameUserInActivity.text=null
    var circleImage =
        activity.findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
    circleImage.setImageDrawable(null)

}

fun showUserNameAdnImage(activity: Activity)
{

    val bd: BitmapDrawable = activity!!.resources.getDrawable(R.drawable.user) as BitmapDrawable
    var name=STORAGE.name
    var nameUserInActivity =
        activity.findViewById<TextView>(R.id.textViewNameforActivityMain)
    nameUserInActivity.text=name
    var circleImage =
        activity.findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
    circleImage.downloadSetImage(STORAGE.photo)


}

fun CircleImageView.downloadSetImage(url:String)
{
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.user)   ////// запись в картинку
        .into(this)

}

fun ImageView.downloadSetImage(url: String)
{
    if(url.isNotEmpty()) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.user)   ////// запись в картинку
            .into(this)
    }

}



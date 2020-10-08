package com.example.povar.ui

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.widget.*
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_item_recept.view.*
import kotlinx.android.synthetic.main.list_item_users.view.*
import kotlin.math.acos

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
    val searchEditActivity=activity!!.findViewById<EditText>(R.id.SearchRecept)
    searchEditActivity.layoutParams.height=0
    searchEditActivity.layoutParams.width=0

    val searchButtonActivity=activity!!.findViewById<ImageView>(R.id.SearchReceptButton)
    searchButtonActivity.layoutParams.height=0
    searchButtonActivity.layoutParams.width=0

    val toolbar= activity!!.findViewById<LinearLayout>(R.id.LinearActivity)
    toolbar.layoutParams.height=160
    var nameUserInActivity =
        activity.findViewById<TextView>(R.id.textViewNameforActivityMain)
    nameUserInActivity.text=null
    var circleImage =
        activity.findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
    circleImage.setImageDrawable(null)

}

fun showUserNameAdnImage(activity: Activity)
{

    val searchEditActivity=activity!!.findViewById<EditText>(R.id.SearchRecept)
    searchEditActivity.layoutParams.height=60
    searchEditActivity.layoutParams.width=400

    val searchButtonActivity=activity!!.findViewById<ImageView>(R.id.SearchReceptButton)
    searchButtonActivity.layoutParams.height=50
    searchButtonActivity.layoutParams.width=50

    val toolbar= activity!!.findViewById<LinearLayout>(R.id.LinearActivity)
    toolbar.layoutParams.height=310

    val bd: BitmapDrawable = activity!!.resources.getDrawable(R.drawable.user) as BitmapDrawable
    var name=STORAGE.name
    var nameUserInActivity =
        activity.findViewById<TextView>(R.id.textViewNameforActivityMain)
    nameUserInActivity.text=name
    var circleImage =
        activity.findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
    if(STORAGE.photo.isNotEmpty()) {
        circleImage.downloadSetImage(STORAGE.photo)
    }
    else{circleImage.setBackgroundResource(R.drawable.user)}


}

fun CircleImageView.downloadSetImage(url:String)
{
    if(url.isNotEmpty()) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.user)   ////// запись в картинку
            .into(this)
    }
    else{

        Picasso.get()
            .load("https://firebasestorage.googleapis.com/v0/b/povar-ec909.appspot.com/o/profile_image%2Fuser.png?alt=media&token=8ac10e5d-520b-41ab-a926-51e70fb8064c")
            .placeholder(R.drawable.user)   ////// запись в картинку
            .into(this)
    }



}

fun ImageView.downloadSetImage(url: String)
{
    if(url.isNotEmpty()) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.recept)   ////// запись в картинку
            .into(this)
    }

}
fun hideSearchForSettings(activity: Activity)
{
    val searchEditActivity=activity!!.findViewById<EditText>(R.id.SearchRecept)
    searchEditActivity.layoutParams.height=0
    searchEditActivity.layoutParams.width=0

    val searchButtonActivity=activity!!.findViewById<ImageView>(R.id.SearchReceptButton)
    searchButtonActivity.layoutParams.height=0
    searchButtonActivity.layoutParams.width=0
}

fun showSearchForSettings(activity: Activity)
{
    val searchEditActivity=activity!!.findViewById<EditText>(R.id.SearchRecept)
    searchEditActivity.layoutParams.height=60
    searchEditActivity.layoutParams.width=400

    val searchButtonActivity=activity!!.findViewById<ImageView>(R.id.SearchReceptButton)
    searchButtonActivity.layoutParams.height=50
    searchButtonActivity.layoutParams.width=50
}







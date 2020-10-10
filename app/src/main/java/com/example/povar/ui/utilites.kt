package com.example.povar.ui

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


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
    if(STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain=="Main") {
        var heightVerx = convert.pxFromDp(activity!!, 70F)
        val searchEditActivity = activity!!.findViewById<EditText>(R.id.SearchRecept)
        searchEditActivity.setVisibility(View.INVISIBLE)

        val searchButtonActivity = activity!!.findViewById<ImageView>(R.id.SearchReceptButton)
        searchButtonActivity.setVisibility(View.INVISIBLE)

        val toolbar = activity!!.findViewById<LinearLayout>(R.id.LinearActivity)
        toolbar.layoutParams.height = heightVerx.toInt()

        var nameUserInActivity =
            activity.findViewById<TextView>(R.id.textViewNameforActivityMain)
        nameUserInActivity.text = null
        var circleImage =
            activity.findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
        circleImage.setVisibility(View.INVISIBLE)
//    circleImage.setImageDrawable(null)

    }
}

fun showUserNameAdnImage(activity: Activity)
{
    if(STORAGE_FOR_RECYCLE_RECEPT.FlagActivityAdminOrMain=="Main") {
        var heightVerx = convert.pxFromDp(activity!!, 120F)
        val searchEditActivity = activity!!.findViewById<EditText>(R.id.SearchRecept)
        searchEditActivity.setVisibility(View.VISIBLE)

        val searchButtonActivity = activity!!.findViewById<ImageView>(R.id.SearchReceptButton)
        searchButtonActivity.setVisibility(View.VISIBLE)

        val toolbar = activity!!.findViewById<LinearLayout>(R.id.LinearActivity)
        toolbar.layoutParams.height = heightVerx.toInt()


        var name = STORAGE.name
        var nameUserInActivity =
            activity.findViewById<TextView>(R.id.textViewNameforActivityMain)
        nameUserInActivity.text = name

        var circleImage =
            activity.findViewById<CircleImageView>(R.id.circleImageViewForActivityMain)
        circleImage.setVisibility(View.VISIBLE)
    }
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
    val searchEditActivity=activity.findViewById<EditText>(R.id.SearchRecept)
    searchEditActivity.setVisibility(View.INVISIBLE)

    val searchButtonActivity=activity.findViewById<ImageView>(R.id.SearchReceptButton)
    searchButtonActivity.setVisibility(View.INVISIBLE)
}

fun showSearchForSettings(activity: Activity)
{
    val searchEditActivity=activity.findViewById<EditText>(R.id.SearchRecept)
    searchEditActivity.setVisibility(View.VISIBLE)

    val searchButtonActivity=activity.findViewById<ImageView>(R.id.SearchReceptButton)
    searchButtonActivity.setVisibility(View.VISIBLE)
}







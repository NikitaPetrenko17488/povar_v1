package com.example.povar.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.povar.R
import com.example.povar.models.Recept
import com.example.povar.models.Stars
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view_one_recept_in_recycle.*

var ocenka=0
private var MassivStarsAllsrdn = mutableListOf<Stars>()
private var MassivStars = mutableListOf<Stars>()
var counterStars:Int =0
var counerStarsAll:Int=0

class ViewOneReceptInRecycle : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MassivStars.removeAll { true }
        MassivStarsAllsrdn.removeAll { true }
        counterStars=0
        counerStarsAll=0
        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_one_recept_in_recycle, container, false)
    }

    override fun onStart() {
        super.onStart()
        initStars()
        Avotzapolnenie()
        hideSettings(activity!!)
        hideSearch(activity!!)
        hideUserNameAdnImage(activity!!)

        Star1.setOnClickListener { ocenka=1
            initBase()
            replaceFragment(ViewOneReceptInRecycle())}
        Star2.setOnClickListener { ocenka=2
            initBase()
            replaceFragment(ViewOneReceptInRecycle())}
        Star3.setOnClickListener { ocenka=3
            initBase()
            replaceFragment(ViewOneReceptInRecycle())}
        Star4.setOnClickListener { ocenka=4
            initBase()
            replaceFragment(ViewOneReceptInRecycle())}
        Star5.setOnClickListener { ocenka=5
            initBase()
            replaceFragment(ViewOneReceptInRecycle())}



    }

    override fun onStop() {
        super.onStop()
        MassivStars.removeAll { true }
        MassivStarsAllsrdn.removeAll { true }
        counterStars=0
        counerStarsAll=0
        STORAGE_STARS.TRUE=0
    }


        private fun initStars( ) {

            REF_DABATABSE_ROOT.child(NODE_STARS)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        showToast("Нет подключения к базе..")
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot: DataSnapshot in dataSnapshot.children) {
                            val stars = snapshot.getValue(Stars::class.java) ?: Stars()

                            if(stars.name_recept==STORAGE_FOR_RECYCLE_RECEPT.name && stars.name_user==STORAGE.ID)
                            {
                                MassivStars.add(stars)
                                STORAGE_STARS.TRUE=1

                            }

                            if(stars.name_recept==STORAGE_FOR_RECYCLE_RECEPT.name)
                            {
                                MassivStarsAllsrdn.add(stars)
                                counerStarsAll++
                            }
                            counterStars++

                        }
                        avtoZapalnenZvezd()
                    }
                })


        }


    fun Avotzapolnenie() {

        var avtozagrNameViewRecept =
            activity!!.findViewById<TextView>(R.id.EditTextNameView)
        avtozagrNameViewRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.name)
        var avtozagrIngridientsViewRecept =
            activity!!.findViewById<TextView>(R.id.EditTextIngridientView)
        avtozagrIngridientsViewRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.ingridients)
        var avtozagrFormulaViewRecept =
            activity!!.findViewById<TextView>(R.id.EditTextFormulaView)
        avtozagrFormulaViewRecept.setText(STORAGE_FOR_RECYCLE_RECEPT.formula)
        var avtozagrImageUpdateRecept =
            activity!!.findViewById<ImageView>(R.id.ImageViewRecept)
        avtozagrImageUpdateRecept.downloadSetImage(STORAGE_FOR_RECYCLE_RECEPT.photo)


        if(STORAGE_FOR_RECYCLE_RECEPT.fragmentContext=="My"||STORAGE_FOR_RECYCLE_RECEPT.fragmentContext=="Admin")
        {
            TextOcenka.setText("")
            NullStars()
        }
        if(STORAGE_FOR_RECYCLE_RECEPT.user_id==STORAGE.ID)
        {
            TextOcenka.setText("")
            NullStars()
        }


    }
fun avtoZapalnenZvezd()
{

    var count=0
    var srdn=0
    if(MassivStarsAllsrdn.isNotEmpty()) {
        while (count< counerStarsAll) {
            srdn = srdn + MassivStarsAllsrdn[count].ocenka
            count++
        }
        srdn=srdn/counerStarsAll
    }

    if (activity!=null)
    activity!!.OcenkaAll.setText(srdn.toString())


    if(STORAGE_FOR_RECYCLE_RECEPT.fragmentContext=="All"&& STORAGE_FOR_RECYCLE_RECEPT.user_id != STORAGE.ID)
     {
         Log.d("xuy1", STORAGE_FOR_RECYCLE_RECEPT.user_id)
         Log.d("xuy2",STORAGE.ID)

         if (activity!=null)
             activity!!.TextOcenka.setText("Ваша оценка:")

        if (STORAGE_STARS.TRUE == 1 ) {
            NullStars()


            if (MassivStars[0].ocenka == 1) {
                if(activity!=null)
                    activity!!.Star1.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star2.setBackgroundResource(R.drawable.star_gray)
                if(activity!=null)
                    activity!!.Star3.setBackgroundResource(R.drawable.star_gray)
                if(activity!=null)
                    activity!!.Star4.setBackgroundResource(R.drawable.star_gray)
                if(activity!=null)
                    activity!!.Star5.setBackgroundResource(R.drawable.star_gray)
            } else if (MassivStars[0].ocenka == 2) {
                if(activity!=null)
                    activity!!.Star1.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star2.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star3.setBackgroundResource(R.drawable.star_gray)
                if(activity!=null)
                    activity!!.Star4.setBackgroundResource(R.drawable.star_gray)
                if(activity!=null)
                    activity!!.Star5.setBackgroundResource(R.drawable.star_gray)
            } else if (MassivStars[0].ocenka == 3) {
                if(activity!=null)
                    activity!!.Star1.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star2.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star3.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star4.setBackgroundResource(R.drawable.star_gray)
                if(activity!=null)
                    activity!!.Star5.setBackgroundResource(R.drawable.star_gray)
            } else if (MassivStars[0].ocenka == 4) {
                if(activity!=null)
                    activity!!.Star1.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star2.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star3.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star4.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star5.setBackgroundResource(R.drawable.star_gray)
            } else if (MassivStars[0].ocenka == 5) {
                if(activity!=null)
                    activity!!.Star1.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star2.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star3.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star4.setBackgroundResource(R.drawable.star_gold)
                if(activity!=null)
                    activity!!.Star5.setBackgroundResource(R.drawable.star_gold)
            }
        } else {

            if(activity!=null)
                activity!!.Star1.setBackgroundResource(R.drawable.star_gray)
            if(activity!=null)
                activity!!.Star2.setBackgroundResource(R.drawable.star_gray)
            if(activity!=null)
                activity!!.Star3.setBackgroundResource(R.drawable.star_gray)
            if(activity!=null)
                activity!!.Star4.setBackgroundResource(R.drawable.star_gray)
            if(activity!=null)
                activity!!.Star5.setBackgroundResource(R.drawable.star_gray)
        }
    }


}




    fun initBase()
    {
        val dateMap =
            mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
        dateMap[CHIELD_STARS_NAME_RECEPT] = STORAGE_FOR_RECYCLE_RECEPT.name
        dateMap[CHIELD_STARS_NAME_USER]=STORAGE.ID
        dateMap[CHIELD_STARS_OCENKA]= ocenka
        REF_DABATABSE_ROOT.child(NODE_STARS).child(STORAGE.ID+STORAGE_FOR_RECYCLE_RECEPT.name).updateChildren(dateMap)

    }

fun NullStars(){
    if(activity!=null)
    activity!!.Star1.setImageDrawable(null)
    if(activity!=null)
    activity!!.Star2.setImageDrawable(null)
    if(activity!=null)
    activity!!.Star3.setImageDrawable(null)
    if(activity!=null)
    activity!!.Star4.setImageDrawable(null)
    if(activity!=null)
    activity!!.Star5.setImageDrawable(null)
}

}


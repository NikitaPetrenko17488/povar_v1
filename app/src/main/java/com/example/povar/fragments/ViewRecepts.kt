package com.example.povar.fragments
import DataAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.argb
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window.FEATURE_NO_TITLE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.activity.MainActivity


import com.example.povar.models.Recept
import com.example.povar.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_alert_delete_dark.view.*
import kotlinx.android.synthetic.main.dialog_alert_delete_lite.view.*
import kotlinx.android.synthetic.main.fragment_view1.*

interface Click
{
    fun updateRecycle()

    fun deletteRecycle()

    fun viewRecycle()

}


private var Massiv = mutableListOf<Recept>()
  var counter:Int =0



class fragment5 : Fragment(),Click {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        showSettings(activity!!)
        showSearch(activity!!)
        showUserNameAdnImage(activity!!)
        showAddButton(activity!!)

        Massiv.removeAll { true }
        counter=0


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_view1, container,false)

    }

    override fun onStart() {
        super.onStart()

        //deleteOfflineBase()
        showSettings(activity!!)
        showSearch(activity!!)
        showUserNameAdnImage(activity!!)
        showAddButton(activity!!)

         initRecepts()
        if(STORAGE.Tema==true)
            Constraint_view.setBackgroundResource(R.drawable.background_fon_fragment_dark_them)
        else
            Constraint_view.setBackgroundResource(R.drawable.background_fon_na_fragment_lite)
        activity!!.addRecept.setOnClickListener{findNavController().navigate(R.id.fragment2)}
        activity!!.SearchReceptButton.setOnClickListener { SearchMyRecept() }


        RefreshMyRecept.setOnRefreshListener {

            replaceFragment(fragment5())
            if (activity!=null)
            activity!!.circleImageViewForActivityMain.downloadSetImage(STORAGE.photo)

        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
        Massiv.removeAll { true }
        counter=0
        //hideAddButton(activity!!)


    }



    override fun onPause() {
        super.onPause()

        Massiv.removeAll { true }
        counter=0



    }



    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_recept, parent, false)) {
        private var mName: TextView? = null
         private var mPhoto: ImageView? = null
        private var mEdit:Button?=null
        private var mDellite:Button?=null

        init {

            mEdit=itemView.findViewById(R.id.editReceptItem)
            mDellite=itemView.findViewById(R.id.deletteReceptItem)
            mName = itemView.findViewById(R.id.name_recept)
            mPhoto=itemView.findViewById(R.id.image_src)

        }
        @SuppressLint("ResourceAsColor")
        fun bind(movie: Recept) {
            if(STORAGE.Tema==true) {
                mName?.setTextColor(Color.parseColor("#b2b2b2"))
                mEdit?.setBackgroundResource(R.drawable.edit_gray)
                mDellite?.setBackgroundResource(R.drawable.delette_gray)

            }
            else
            {
                mName?.setTextColor(Color.parseColor("#000000"))
                mEdit?.setBackgroundResource(R.drawable.edit)
                mDellite?.setBackgroundResource(R.drawable.delette)
            }

            mName?.text = movie.name
            mPhoto?.downloadSetImage(movie.photoUrl)
        }


    }


   fun create_recycle() {



           if (Massiv.isEmpty()) {
               if(activity!=null)
               activity!!.ToastNoRecepts.text = " Нет рецептов "
           } else {
               if(activity!=null)
                  activity!!.ToastNoRecepts.text = " "
           }
           if (recicle_view_recept != null) {
               activity!!.recicle_view_recept.apply {
                   layoutManager = LinearLayoutManager(activity)
                   adapter = DataAdapter(Massiv, this@fragment5)

               }
           }



   }


    private fun initRecepts( ) {

        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    showToast("Нет подключения к базе..")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()
                        if(recept.user_id==STORAGE.ID) {
                            Massiv.add(recept)
                        }
                        counter++

                        if (counter>0)
                            create_recycle()
                    }

                }

            })

    }



    override fun updateRecycle() {
       replaceFragment(fragment3())


    }




    override fun deletteRecycle() {


        val builder=AlertDialog.Builder(activity)
        if(STORAGE.Tema==true){

            val mDialogView = LayoutInflater.from (activity) .inflate (R.layout.dialog_alert_delete_dark, null )


                builder.setView(mDialogView)
            val exitAndShow=builder.show()
            mDialogView.buttonOtmenaAlertDialogDark.setOnClickListener {
                exitAndShow.dismiss()
            }
            mDialogView.buttonDeleteAlertDialogDark.setOnClickListener {
                deleteReceptForRecyclerForAlertDialog()
                exitAndShow.dismiss()

            }
        }
        if(STORAGE.Tema==false){
            val mDialogView = LayoutInflater.from (activity) .inflate (R.layout.dialog_alert_delete_lite, null )
            builder.setView(mDialogView)
            val exitAndShow=builder.show()
            mDialogView.buttonOtmenaAlertDialogLite.setOnClickListener{
                exitAndShow.dismiss()
            }
            mDialogView.buttonDeleteAlertDialogLite.setOnClickListener {

                deleteReceptForRecyclerForAlertDialog()
                exitAndShow.dismiss()
            }
        }


    }

    override fun viewRecycle() {
        findNavController().navigate(R.id.viewOneReceptInRecycle)


    }

    private fun deleteReceptForRecyclerForAlertDialog()
    {
        REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(STORAGE_FOR_RECYCLE_RECEPT.ID)
            .removeValue { error, ref ->  }

        val dateMapUser =
            mutableMapOf<String, Any>() //создаем мапу , что бы разом передать в бд
        STORAGE.counter_recept=STORAGE.counter_recept-1
        dateMapUser[CHIELD_COUNTER_RECEPT]=STORAGE.counter_recept

        REF_DABATABSE_ROOT.child(NODE_USERS).child(STORAGE.ID).updateChildren(dateMapUser)

        replaceFragment(fragment5())
    }

  private  fun SearchMyRecept() {
        if (STORAGE_FOR_RECYCLE_RECEPT.fragmentContext == "My") {
            counter = 0
            Massiv.removeAll { true }

            REF_DABATABSE_ROOT.child(NODE_RECEPTS)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        showToast("Нет подключения к базе..")
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot: DataSnapshot in dataSnapshot.children) {
                            val recept = snapshot.getValue(Recept::class.java) ?: Recept()

                            var receptIngridientsUp=recept.ingridients.toUpperCase()
                            var vxodStroki: String
                            vxodStroki = activity!!.SearchRecept.text.toString().toUpperCase()

                            var indexIngridient: Boolean = receptIngridientsUp.contains(vxodStroki)


                            if (recept.user_id == STORAGE.ID && indexIngridient == true ) {
                                Massiv.add(recept)
                            } else if (vxodStroki.isEmpty()) {

                                if (recept.user_id == STORAGE.ID) {
                                    Massiv.add(recept)
                                }
                            }


                            counter++

                            if (counter > 0)
                                create_recycle()


                        }

                    }

                })
        }
    }



}
package com.example.povar.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.Recept
import com.example.povar.ui.NODE_RECEPTS
import com.example.povar.ui.REF_DABATABSE_ROOT
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_view1.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var Massiv = mutableListOf<Recept>()

class fragment5 : Fragment() {


    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_view1, container, false)
    }

    override fun onStart() {
        super.onStart()
        initRecepts()
        //create_recycle()
    }

   fun create_recycle()
    {
        val rv = view!!.findViewById(R.id.rv) as RecyclerView
        val llm = LinearLayoutManager(context)
        rv.layoutManager = llm

    }

    private fun initRecepts() {

        REF_DABATABSE_ROOT.child(NODE_RECEPTS)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "Нет подключения к базе..", Toast.LENGTH_SHORT).show()
                }
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot: DataSnapshot in dataSnapshot.children) {
                        val recept = snapshot.getValue(Recept::class.java) ?: Recept()
                        Massiv.add(recept)

                    }

                    var count = 0

                    for (index in Massiv.withIndex()) {
                        TextViewView.text = String.format("%s%s%s%s", TextViewView.text, "Название: ", Massiv[count].name, "\n\n")
                        TextViewView.text = String.format("%s%s%s%s", TextViewView.text, "Ингридиенты: ", Massiv[count].ingridients, "\n\n")
                        TextViewView.text = String.format("%s%s%s%s", TextViewView.text, "Рецепт: ", Massiv[count].formula, "\n\n\n\n")
                        count++
                        //
                    }
                    Massiv.removeAll { true }
                }

            })

    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment5().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.povar.fragments
import DataAdapter
import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
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

    private val mNicolasCageMovies = listOf(
        Movie("Raising Arizona", 1987),
        Movie("Vampire's Kiss", 1988),
        Movie("Con Air", 1997),
        Movie("Gone in 60 Seconds", 1997),
        Movie("National Treasure", 2004),
        Movie("The Wicker Man", 2006),
        Movie("Ghost Rider", 2007),
        Movie("Knowing", 2009)
    )

    data class Movie(val title: String, val year: Int)

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
        create_recycle()
        initRecepts()

    }

    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mYearView: TextView? = null
        init {
            mTitleView = itemView.findViewById(R.id.name_recept)
            mYearView = itemView.findViewById(R.id.formula_recept)
        }
        fun bind(movie: Movie) {
            mTitleView?.text = movie.title
            mYearView?.text = movie.year.toString()
        }
    }
   fun create_recycle() {

            rv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = DataAdapter(mNicolasCageMovies)
            }
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
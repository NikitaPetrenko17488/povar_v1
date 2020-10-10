package com.example.povar.fragments

import DataAdapterCalorii
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.R
import com.example.povar.models.Calorii
import com.example.povar.ui.hideSearch
import com.example.povar.ui.showSettings
import com.example.povar.ui.showUserNameAdnImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_kalorii.*

private val mNicolasCageMovies = listOf(
    Calorii("белый гриб", "34"),
    Calorii("бекон", "500"),
    Calorii("говядина", "250"),
    Calorii("горох", "320"),
    Calorii("индейка", "189"),
    Calorii("кукурузная крупа", "320"),
    Calorii("курица", "214"),
    Calorii("колбаса варенная", "223"),
    Calorii("колбаса сырокопченая", "450"),
    Calorii("каша гречневая", "120"),
    Calorii("манная крупа", "333"),
    Calorii("мюсли", "400"),
    Calorii("свинина", "144"),
    Calorii("фасоль", "350"),
    Calorii("ячменная каша", "400"),

    Calorii("огурец", "16"),
    Calorii("помидор", "18"),
    Calorii("перец", "40"),
    Calorii("лук", "40"),
    Calorii("морковь", "35")
)
class Kalorii : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showSettings(activity!!)
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kalorii, container, false)
    }


    override fun onStart() {
        super.onStart()

        showSettings(activity!!)
        showUserNameAdnImage(activity!!)
        hideSearch(activity!!)

        create_recycle()
    }

    override fun onStop() {
        super.onStop()
            //showSearchForSettings(activity!!)
    }

    class MovieViewHolderCalorii(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_kalorii, parent, false)) {
        private var mName: TextView? = null
        private var mCalorii: TextView? = null


        init {

            mName = itemView.findViewById(R.id.NazvanieKalorii)
            mCalorii = itemView.findViewById(R.id.KaloriiKalorii)


        }
        fun bind(movie: Calorii) {

            mName?.text = movie.name.toString()
            mCalorii?.text = movie.kalorii.toString()


        }


    }


    fun create_recycle() {

        recyclerCalorii.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DataAdapterCalorii(mNicolasCageMovies as MutableList<Calorii>)

        }

    }
}
package com.example.povar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.povar.R
import com.example.povar.ui.NODE_RECEPTS
import com.example.povar.ui.REF_DABATABSE_ROOT
import com.example.povar.ui.replaceFragment
import kotlinx.android.synthetic.main.fragment_delete.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fragment4 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {
        super.onStart()
        delete_buton.setOnClickListener { deleteRecept() }  // если клик по кнопке вызвать метод
    }

    private fun deleteRecept()
    {
        if (DeleteEditText.text.toString().isEmpty()) {  // если пусто в названии блюда, то
            Toast.makeText(activity, "Введите название бюда", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val name =
                DeleteEditText.text.toString() //запись в перемнную из ЕдитТекст

            REF_DABATABSE_ROOT.child(NODE_RECEPTS).child(name)
                .removeValue { error, ref ->  }
           replaceFragment(fragment1())
            Toast.makeText(activity, "Блюдо успешно удалено..", Toast.LENGTH_SHORT).show()

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment4().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
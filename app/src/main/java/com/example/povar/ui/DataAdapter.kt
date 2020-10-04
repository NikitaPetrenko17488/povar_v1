import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.povar.fragments.Click

import com.example.povar.fragments.ViewUsersForAdmin
import com.example.povar.fragments.fragment5

import com.example.povar.models.Recept
import com.example.povar.models.User
import com.example.povar.ui.STORAGE_FOR_RECYCLE_RECEPT
import kotlinx.android.synthetic.main.list_item_recept.view.*


class DataAdapter(private val list: MutableList<Recept>, private val click:Click)
    : RecyclerView.Adapter<fragment5.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fragment5.MovieViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return fragment5.MovieViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: fragment5.MovieViewHolder, position: Int) {

        val movie: Recept = list[position]
        holder.bind(movie)

        holder.itemView.editReceptItem.setOnClickListener{

            STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
            STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
            STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoSrc
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.updateRecycle()

        }

        holder.itemView.deletteReceptItem.setOnClickListener{

            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.deletteRecycle()

        }



    }
    override fun getItemCount(): Int = list.size

}



class DataAdapterUsers(private val list: MutableList<User>)
    : RecyclerView.Adapter<ViewUsersForAdmin.MovieViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewUsersForAdmin.MovieViewHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        return ViewUsersForAdmin.MovieViewHolder2(inflater, parent)
    }
    override fun onBindViewHolder(holder: ViewUsersForAdmin.MovieViewHolder2, position: Int) {
        val movie2: User = list[position]
        holder.bind(movie2)
    }
    override fun getItemCount(): Int = list.size

}
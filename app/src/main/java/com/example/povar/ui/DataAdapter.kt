import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.fragments.*
import com.example.povar.models.Calorii

import com.example.povar.models.Recept
import com.example.povar.models.User
import com.example.povar.ui.STORAGE_FOR_RECYCLE_RECEPT
import kotlinx.android.synthetic.main.list_item_recept.view.*
import kotlinx.android.synthetic.main.list_item_recept_all.view.*


class DataAdapter(private val list: MutableList<Recept>, private val click: Click)
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
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.updateRecycle()

        }

        holder.itemView.deletteReceptItem.setOnClickListener{

            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.deletteRecycle()

        }



        holder.itemView.kek.setOnClickListener{
            STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
            STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
            STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.viewRecycle()

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



class DataAdapterAll(private val list: MutableList<Recept>, private val clickAll: ClickAll)
    : RecyclerView.Adapter<ViewAllRecept.MovieViewHolderAll>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllRecept.MovieViewHolderAll {

        val inflater = LayoutInflater.from(parent.context)
        return ViewAllRecept.MovieViewHolderAll(inflater, parent)
    }
    override fun onBindViewHolder(holder: ViewAllRecept.MovieViewHolderAll, position: Int) {

        val movie: Recept = list[position]
        holder.bind(movie)


        holder.itemView.Constraint_layout_all.setOnClickListener {
            STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
            STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
            STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            clickAll.viewRecycle()
        }

    }
    override fun getItemCount(): Int = list.size

}


class DataAdapterCalorii(private val list: MutableList<Calorii>)
    : RecyclerView.Adapter<Kalorii.MovieViewHolderCalorii>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Kalorii.MovieViewHolderCalorii {

        val inflater = LayoutInflater.from(parent.context)
        return Kalorii.MovieViewHolderCalorii(inflater, parent)
    }
    override fun onBindViewHolder(holder: Kalorii.MovieViewHolderCalorii, position: Int) {

        val movie: Calorii = list[position]
        holder.bind(movie)



    }
    override fun getItemCount(): Int = list.size

}
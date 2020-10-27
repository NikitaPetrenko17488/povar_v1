import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.fragments.*
import com.example.povar.models.CalculatorKalorii
import com.example.povar.models.Calorii

import com.example.povar.models.Recept
import com.example.povar.models.User
import com.example.povar.ui.STORAGE_CALKULATOR
import com.example.povar.ui.STORAGE_FOR_RECYCLE_RECEPT
import com.example.povar.ui.STORAGE_USERS_FOR_ADMIN
import kotlinx.android.synthetic.main.list_calculator_kalorii.view.*
import kotlinx.android.synthetic.main.list_item_recept.view.*
import kotlinx.android.synthetic.main.list_item_recept_all.view.*
import kotlinx.android.synthetic.main.list_item_recept_translate.view.*
import kotlinx.android.synthetic.main.list_item_users.view.*


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
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            STORAGE_FOR_RECYCLE_RECEPT.nameEng=movie.name_eng
            STORAGE_FOR_RECYCLE_RECEPT.ingridientsEng=movie.ingridients_eng
            STORAGE_FOR_RECYCLE_RECEPT.formulaEng=movie.formula_eng
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
            STORAGE_FOR_RECYCLE_RECEPT.nameEng=movie.name_eng
            STORAGE_FOR_RECYCLE_RECEPT.ingridientsEng=movie.ingridients_eng
            STORAGE_FOR_RECYCLE_RECEPT.formulaEng=movie.formula_eng
            click.viewRecycle()

        }



    }
    override fun getItemCount(): Int = list.size

}



class DataAdapterUsers(private val list: MutableList<User>,private val ClickAdmin:ViewAdmin)
    : RecyclerView.Adapter<ViewUsersForAdmin.MovieViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewUsersForAdmin.MovieViewHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        return ViewUsersForAdmin.MovieViewHolder2(inflater, parent)
    }
    override fun onBindViewHolder(holder: ViewUsersForAdmin.MovieViewHolder2, position: Int) {
        val movie2: User = list[position]

        holder.itemView.buttonGlaz.setOnClickListener {
            STORAGE_USERS_FOR_ADMIN.ID=movie2.id
            STORAGE_USERS_FOR_ADMIN.counter_recept=movie2.counter_recept
            STORAGE_USERS_FOR_ADMIN.login=movie2.login
            STORAGE_USERS_FOR_ADMIN.name=movie2.name
            STORAGE_USERS_FOR_ADMIN.photo=movie2.photoUrl
            STORAGE_USERS_FOR_ADMIN.password=movie2.password
            ClickAdmin.ViewUser()

        }
        holder.itemView.buttonDelite.setOnClickListener {
            STORAGE_USERS_FOR_ADMIN.ID=movie2.id
            ClickAdmin.DeletteUser()
        }
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
            STORAGE_FOR_RECYCLE_RECEPT.user_id=movie.user_id
            STORAGE_FOR_RECYCLE_RECEPT.nameEng=movie.name_eng
            STORAGE_FOR_RECYCLE_RECEPT.ingridientsEng=movie.ingridients_eng
            STORAGE_FOR_RECYCLE_RECEPT.formulaEng=movie.formula_eng
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

class DataAdapterReceptsUserForAdmin(private val list: MutableList<Recept>, private val click: ViewProfileUsersForAdmin)
    : RecyclerView.Adapter<ViewProfileUsersForAdmin.MovieViewHolderReceptsUserForAdmin>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewProfileUsersForAdmin.MovieViewHolderReceptsUserForAdmin {

        val inflater = LayoutInflater.from(parent.context)
        return ViewProfileUsersForAdmin.MovieViewHolderReceptsUserForAdmin(inflater, parent)
    }
    override fun onBindViewHolder(holder: ViewProfileUsersForAdmin.MovieViewHolderReceptsUserForAdmin, position: Int) {

        val movie: Recept = list[position]
        holder.bind(movie)

        holder.itemView.editReceptItem.setOnClickListener{

            STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
            STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
            STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.EditReceptsUserForAdmin()

        }

        holder.itemView.deletteReceptItem.setOnClickListener{

            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.DeletteReceptsUserForAdmin()

        }


        holder.itemView.kek.setOnClickListener{
            STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
            STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
            STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            click.ViewReceptsUserForAdmin()

        }

    }

    override fun getItemCount(): Int=list.size
}

class DataAdapterOffline(private val list: MutableList<Recept>, private val clickOff:ClickOffline)
    : RecyclerView.Adapter<offline_avtonomnoe.MovieViewHolderOffline>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): offline_avtonomnoe.MovieViewHolderOffline {

        val inflater = LayoutInflater.from(parent.context)
        return offline_avtonomnoe.MovieViewHolderOffline(inflater, parent)
    }
    override fun onBindViewHolder(holder: offline_avtonomnoe.MovieViewHolderOffline, position: Int) {

        val movie: Recept = list[position]
        holder.bind(movie)


        holder.itemView.Constraint_layout_all.setOnClickListener {
            STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
            STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
            STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            STORAGE_FOR_RECYCLE_RECEPT.user_id=movie.user_id

            clickOff.ViewRecept()

        }

    }
    override fun getItemCount(): Int = list.size

}


class DataAdapterTranslate(private val list: MutableList<Recept>, private val clickOff:ClickTranslate)
    : RecyclerView.Adapter<Translater.TranslateReceptHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Translater.TranslateReceptHolder {

        val inflater = LayoutInflater.from(parent.context)
        return Translater.TranslateReceptHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: Translater.TranslateReceptHolder, position: Int) {

        val movie: Recept = list[position]
        holder.bind(movie)


        holder.itemView.Constraint_layout_translate.setOnClickListener {
            STORAGE_FOR_RECYCLE_RECEPT.name=movie.name
            STORAGE_FOR_RECYCLE_RECEPT.formula=movie.formula
            STORAGE_FOR_RECYCLE_RECEPT.ingridients=movie.ingridients
            STORAGE_FOR_RECYCLE_RECEPT.photo=movie.photoUrl
            STORAGE_FOR_RECYCLE_RECEPT.ID=movie.id
            STORAGE_FOR_RECYCLE_RECEPT.nameEng=movie.name_eng
            STORAGE_FOR_RECYCLE_RECEPT.ingridientsEng=movie.ingridients_eng
            STORAGE_FOR_RECYCLE_RECEPT.formulaEng=movie.formula_eng

            clickOff.TranslateRecycle()

        }

    }
    override fun getItemCount(): Int = list.size

}
class DataAdapterCalkulatorKalorii(private val list: MutableList<CalculatorKalorii>)
    : RecyclerView.Adapter<CalkulatorKalorii.ViewHolderCalkulatorCalorii>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalkulatorKalorii.ViewHolderCalkulatorCalorii {

        val inflater = LayoutInflater.from(parent.context)
        return CalkulatorKalorii.ViewHolderCalkulatorCalorii(inflater, parent)
    }
    override fun onBindViewHolder(holder: CalkulatorKalorii.ViewHolderCalkulatorCalorii, position: Int) {

        val movie: CalculatorKalorii = list[position]
        holder.bind(movie)


    }
    override fun getItemCount(): Int = list.size

}
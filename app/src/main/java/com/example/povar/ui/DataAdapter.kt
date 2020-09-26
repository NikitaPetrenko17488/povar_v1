import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.fragments.fragment5
import com.example.povar.models.Recept

class DataAdapter(private val list: MutableList<Recept>)
    : RecyclerView.Adapter<fragment5.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fragment5.MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return fragment5.MovieViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: fragment5.MovieViewHolder, position: Int) {
        val movie: Recept = list[position]
        holder.bind(movie)
    }
    override fun getItemCount(): Int = list.size
}
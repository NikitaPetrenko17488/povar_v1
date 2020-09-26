import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.povar.fragments.fragment5

class DataAdapter(private val list: List<fragment5.Movie>)
    : RecyclerView.Adapter<fragment5.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fragment5.MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return fragment5.MovieViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: fragment5.MovieViewHolder, position: Int) {
        val movie: fragment5.Movie = list[position]
        holder.bind(movie)
    }
    override fun getItemCount(): Int = list.size
}
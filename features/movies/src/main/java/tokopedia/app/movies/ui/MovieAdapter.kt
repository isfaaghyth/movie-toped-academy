package tokopedia.app.movies.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tokopedia.app.abstraction.util.ext.load
import tokopedia.app.data.entity.Movie
import tokopedia.app.movies.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder.inflate(parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movies[position])
    }

    class Holder(private val view: View): RecyclerView.ViewHolder(view) {
        private val title = view.txt_movie_name
        private val cardItem = view.card_movie
        private val poster = view.img_poster
        private val year = view.txt_year

        companion object {
            fun inflate(parent: ViewGroup): Holder {
                return Holder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_movie,
                        parent,
                        false)
                )
            }
        }

        fun bind(movie: Movie) {
            title.text = movie.title
            year.text = movie.releaseDate
            poster.load(movie.bannerUrl())
            cardItem.setOnClickListener {
                val context = view.context
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("jetmovie://detail/movie/${movie.id}")))
            }
        }
    }

}
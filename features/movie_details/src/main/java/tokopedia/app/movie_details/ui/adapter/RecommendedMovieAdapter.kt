package tokopedia.app.movie_details.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import tokopedia.app.abstraction.util.ext.load
import tokopedia.app.data.entity.Movie
import tokopedia.app.movie_details.R

/**
 * @author by furqan on 02/12/2019
 */
class RecommendedMovieAdapter(private val movieCastList: List<Movie>) :
    RecyclerView.Adapter<RecommendedMovieAdapter.RecommendedMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_recommended, parent, false)
        return RecommendedMovieViewHolder(view)
    }

    override fun getItemCount(): Int = movieCastList.size

    override fun onBindViewHolder(holder: RecommendedMovieViewHolder, position: Int) {
        holder.bind(movieCastList[position])
    }

    class RecommendedMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val moviePoster: AppCompatImageView = itemView.findViewById(R.id.iv_movie)
        private val movieName: AppCompatTextView = itemView.findViewById(R.id.tv_movie)

        fun bind(movie: Movie) {
            moviePoster.load(movie.posterUrl())
            movieName.text = movie.title
        }
    }
}
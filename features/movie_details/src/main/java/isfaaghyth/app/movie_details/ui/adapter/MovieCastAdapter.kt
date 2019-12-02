package isfaaghyth.app.movie_details.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import isfaaghyth.app.abstraction.util.ext.circle
import isfaaghyth.app.data.entity.MovieCast
import isfaaghyth.app.movie_details.R

/**
 * @author by furqan on 02/12/2019
 */
class MovieCastAdapter(private val movieCastList: List<MovieCast>) :
    RecyclerView.Adapter<MovieCastAdapter.MovieClassViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieClassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_cast, parent, false)
        return MovieClassViewHolder(view)
    }

    override fun getItemCount(): Int = movieCastList.size

    override fun onBindViewHolder(holder: MovieClassViewHolder, position: Int) {
        holder.bind(movieCastList[position])
    }

    class MovieClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val castProfilePhoto: AppCompatImageView = itemView.findViewById(R.id.iv_cast_profile)
        private val castName: AppCompatTextView = itemView.findViewById(R.id.tv_cast_name)

        fun bind(cast: MovieCast) {
            castProfilePhoto.circle(cast.profilePhoto)
            castName.text = cast.name
        }
    }
}
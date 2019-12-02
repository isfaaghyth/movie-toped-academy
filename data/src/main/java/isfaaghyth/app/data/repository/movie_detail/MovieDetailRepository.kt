package isfaaghyth.app.data.repository.movie_detail

import isfaaghyth.app.data.entity.Credits
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.TVShow
import retrofit2.Response

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: String): Response<Movie>
    suspend fun getTVShowDetail(movieId: String): Response<TVShow>
    suspend fun getMovieCredits(movieId: String): Response<Credits>
}
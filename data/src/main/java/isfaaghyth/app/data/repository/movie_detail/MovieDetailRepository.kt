package isfaaghyth.app.data.repository.movie_detail

import isfaaghyth.app.data.entity.Credits
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.RateMovieParam
import isfaaghyth.app.data.entity.RateMovieResponse
import isfaaghyth.app.data.entity.TVShow
import retrofit2.Response

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: String): Response<Movie>
    suspend fun getTVShowDetail(movieId: String): Response<TVShow>
    suspend fun rateMovie(movieId: String, starRating: RateMovieParam, guestId: String): Response<RateMovieResponse>
    suspend fun getMovieCredits(movieId: String): Response<Credits>
}
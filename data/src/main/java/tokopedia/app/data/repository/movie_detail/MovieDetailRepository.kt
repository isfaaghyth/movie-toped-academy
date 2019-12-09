package tokopedia.app.data.repository.movie_detail

import tokopedia.app.data.entity.Credits
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.RateMovieParam
import tokopedia.app.data.entity.RateMovieResponse
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.entity.TVShow
import retrofit2.Response

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: String): Response<Movie>
    suspend fun getTVShowDetail(movieId: String): Response<TVShow>
    suspend fun rateMovie(movieId: String, starRating: RateMovieParam, guestId: String): Response<RateMovieResponse>
    suspend fun getMovieCredits(movieId: String): Response<Credits>
    suspend fun getMoviesByGenre(genreIds: String): Response<Movies>
}
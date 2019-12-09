package tokopedia.app.data.repository.movie_detail

import tokopedia.app.data.entity.Credits
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.RateMovieParam
import tokopedia.app.data.entity.RateMovieResponse
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.entity.TVShow
import tokopedia.app.data.routes.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val service: NetworkServices
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: String): Response<Movie> {
        return service.getMovieDetail(movieId)
    }

    override suspend fun getTVShowDetail(movieId: String): Response<TVShow> {
        return service.getTvDetail(movieId)
    }

    override suspend fun getMovieCredits(movieId: String): Response<Credits> {
        return service.getMovieCredits(movieId)
    }
    override suspend fun rateMovie(movieId: String, starRating: RateMovieParam, guestId: String): Response<RateMovieResponse> {
        return service.rateMovie(movieId, starRating, guestId)
    }

    override suspend fun getMoviesByGenre(genreIds: String): Response<Movies> {
        return service.getMovieCreditsByGenre(genreIds)
    }
}
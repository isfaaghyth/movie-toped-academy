package tokopedia.app.data.repository.movie_detail

import retrofit2.Response
import tokopedia.app.data.entity.*
import tokopedia.app.data.routes.NetworkServices

class MovieDetailRepositoryImpl constructor(
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
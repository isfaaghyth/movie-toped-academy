package tokopedia.app.movie_details.domain

import tokopedia.app.abstraction.util.UNSUCCESSFUL_MESSAGE
import tokopedia.app.abstraction.util.ext.fetchState
import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.Credits
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.RateMovieParam
import tokopedia.app.data.entity.RateMovieResponse
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.entity.TVShow
import tokopedia.app.data.repository.movie_detail.MovieDetailRepository
import retrofit2.Response
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(val repository: MovieDetailRepository) {

    suspend fun getMovieDetail(movieId: String): ResultState<Movie> {
        return fetchState {
            wrapperDetail {
                repository.getMovieDetail(movieId)
            }
        }
    }

    suspend fun getTvDetail(movieId: String): ResultState<TVShow> {
        return fetchState {
            wrapperDetail {
                repository.getTVShowDetail(movieId)
            }
        }
    }

    suspend fun getMovieCredits(movieId: String): ResultState<Credits> {
        return fetchState {
            wrapperDetail {
                repository.getMovieCredits(movieId)
            }
        }
    }

    suspend fun getMoviesByGenre(genreIds: String): ResultState<Movies> {
        return fetchState {
            wrapperDetail {
                repository.getMoviesByGenre(genreIds)
            }
        }
    }

    suspend fun rateMovie(movieId: String, starRating: RateMovieParam, guestId: String): ResultState<RateMovieResponse> {
        return fetchState {
            wrapperDetail {
                repository.rateMovie(movieId, starRating, guestId)
            }
        }
    }

    private suspend fun <T: Any> wrapperDetail(call: suspend () -> Response<T>): ResultState<T> {
        return if (call.invoke().isSuccessful) {
            ResultState.Success(call.invoke().body()!!)
        } else {
            ResultState.Error(UNSUCCESSFUL_MESSAGE)
        }
    }

}
package tokopedia.app.movie_details.domain

import tokopedia.app.abstraction.util.UNSUCCESSFUL_MESSAGE
import tokopedia.app.abstraction.util.ext.fetchState
import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.RateMovieParam
import tokopedia.app.data.entity.RateMovieResponse
import tokopedia.app.data.repository.movie_detail.MovieDetailRepository
import retrofit2.Response
import javax.inject.Inject

class RateMovieUseCase @Inject constructor(val repository: MovieDetailRepository) {

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
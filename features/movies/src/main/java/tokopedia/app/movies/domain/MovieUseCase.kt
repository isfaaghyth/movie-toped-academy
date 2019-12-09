package tokopedia.app.movies.domain

import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.abstraction.util.UNSUCCESSFUL_MESSAGE
import tokopedia.app.abstraction.util.ext.fetchState
import tokopedia.app.abstraction.util.session.GuestSessionResponse
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.repository.movie.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun getPopularMovie(): ResultState<Movies> {
        return fetchState {
            val response = repository.getPopularMovie()
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error(UNSUCCESSFUL_MESSAGE)
            }
        }
    }

}
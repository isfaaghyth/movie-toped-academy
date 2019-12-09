package tokopedia.app.tvshows.domain

import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.abstraction.util.UNSUCCESSFUL_MESSAGE
import tokopedia.app.abstraction.util.ext.fetchState
import tokopedia.app.data.entity.TVShows
import tokopedia.app.data.repository.tvshow.TVShowRepository
import javax.inject.Inject

class TVShowUseCase @Inject constructor(private val repository: TVShowRepository) {

    suspend fun getPopularTvShow(): ResultState<TVShows> {
        return fetchState {
            val response = repository.getPopularTVShow()
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error(UNSUCCESSFUL_MESSAGE)
            }
        }
    }

}
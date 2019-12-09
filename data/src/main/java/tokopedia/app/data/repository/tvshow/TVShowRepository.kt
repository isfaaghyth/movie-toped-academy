package tokopedia.app.data.repository.tvshow

import tokopedia.app.data.entity.TVShows
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface TVShowRepository {
    suspend fun getPopularTVShow(): Response<TVShows>
}
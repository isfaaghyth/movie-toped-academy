package tokopedia.app.data.repository.tvshow

import tokopedia.app.data.entity.TVShows
import tokopedia.app.data.routes.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class TVShowRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): TVShowRepository {

    override suspend fun getPopularTVShow(): Response<TVShows> {
        return service.getPopularTVShow()
    }

}
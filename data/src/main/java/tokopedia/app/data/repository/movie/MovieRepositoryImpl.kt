package tokopedia.app.data.repository.movie

import tokopedia.app.data.entity.Movies
import tokopedia.app.data.routes.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): MovieRepository {

    override suspend fun getPopularMovie(): Response<Movies> {
        return service.getPopularMovie()
    }

}
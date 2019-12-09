package tokopedia.app.data.repository.movie

import retrofit2.Response
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.routes.NetworkServices

class MovieRepositoryImpl constructor(
    private val service: NetworkServices
): MovieRepository {

    override suspend fun getPopularMovie(): Response<Movies> {
        return service.getPopularMovie()
    }

}
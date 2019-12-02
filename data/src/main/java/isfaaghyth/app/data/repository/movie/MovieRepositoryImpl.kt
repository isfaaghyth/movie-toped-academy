package isfaaghyth.app.data.repository.movie

import isfaaghyth.app.abstraction.util.session.GuestSessionResponse
import isfaaghyth.app.data.entity.Movies
import isfaaghyth.app.data.routes.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): MovieRepository {

    override suspend fun getPopularMovie(): Response<Movies> {
        return service.getPopularMovie()
    }

    override suspend fun getGuestSessionId(): Response<isfaaghyth.app.abstraction.util.session.GuestSessionResponse> {
        return service.getGuestSessionId()
    }

}
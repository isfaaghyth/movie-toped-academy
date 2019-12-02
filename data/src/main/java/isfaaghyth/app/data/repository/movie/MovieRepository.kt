package isfaaghyth.app.data.repository.movie

import isfaaghyth.app.data.entity.Movies
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovie(): Response<Movies>
    suspend fun getGuestSessionId(): Response<isfaaghyth.app.abstraction.util.session.GuestSessionResponse>
}
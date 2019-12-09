package tokopedia.app.data.repository.movie

import tokopedia.app.data.entity.Movies
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovie(): Response<Movies>
}
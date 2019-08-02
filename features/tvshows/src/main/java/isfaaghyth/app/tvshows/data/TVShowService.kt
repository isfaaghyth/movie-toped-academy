package isfaaghyth.app.tvshows.data

import isfaaghyth.app.tvshows.data.model.TvShows
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowService {

    @GET("tv/popular")
    fun getTVShow(
        @Query("api_key") apiKey: String
    ): Deferred<TvShows>

}
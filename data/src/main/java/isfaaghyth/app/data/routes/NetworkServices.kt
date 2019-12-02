package isfaaghyth.app.data.routes

import isfaaghyth.app.data.BuildConfig
import isfaaghyth.app.data.entity.*
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkServices {

    @GET("movie/popular")
    suspend fun getPopularMovie(): Response<Movies>

    @GET("authentication/guest_session/new")
    suspend fun getGuestSessionId(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<isfaaghyth.app.abstraction.util.session.GuestSessionResponse>

    @GET("tv/popular")
    suspend fun getPopularTVShow(): Response<TVShows>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String
    ): Response<Movie>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: String
    ): Response<Credits>

    @GET("discover/movie")
    suspend fun getMovieCreditsByGenre(@Query("with_genres") genres: String): Response<Movies>

    @GET("tv/{movie_id}")
    suspend fun getTvDetail(
        @Path("movie_id") movieId: String
    ): Response<TVShow>

    @POST("movie/{movie_id}/rating")
    suspend fun rateMovie(
        @Path("movie_id") movieId: String,
        @Body value: RateMovieParam,
        @Query("guest_session_id") guestSessionId: String): Response<RateMovieResponse>

}
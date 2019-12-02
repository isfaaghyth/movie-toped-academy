package isfaaghyth.app.data.routes

import isfaaghyth.app.data.entity.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServices {

    @GET("movie/popular")
    suspend fun getPopularMovie(): Response<Movies>

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

    @GET("tv/{movie_id}")
    suspend fun getTvDetail(
        @Path("movie_id") movieId: String
    ): Response<TVShow>

}
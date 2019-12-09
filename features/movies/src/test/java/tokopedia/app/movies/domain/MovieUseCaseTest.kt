package tokopedia.app.movies.domain

import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.repository.movie.MovieRepository
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class MovieUseCaseTest {

    private var repository = mock(MovieRepository::class.java)
    private lateinit var useCase: MovieUseCase

    private val movies = listOf(
        Movie(
            "id",
            "movieId",
            "title",
            "posterPath",
            "overview",
            "backdrop",
            0,
            0f,
            "relateDate"
        )
    )

    @Before fun setUp() {
        useCase = MovieUseCase(repository)
    }

    @Test fun `should return success`() {
        val actual = ResultState.Success(Movies(movies))
        val result = runBlocking {
            `when`(repository.getPopularMovie())
                .thenReturn(Response.success(Movies(movies)))
            useCase.getPopularMovie()
        }
        assert(result == actual)
    }

    @Test fun `should return error`() {
        val actual = ResultState.Error("")
        val result = runBlocking {
            `when`(repository.getPopularMovie())
                .thenReturn(
                    Response.error(401, ResponseBody.create(MediaType.parse("application/json"), ""))
                )
            useCase.getPopularMovie()
        }

        //probably has different error message, so you can check by type of java class
        assert(result.javaClass === actual.javaClass)
    }

}
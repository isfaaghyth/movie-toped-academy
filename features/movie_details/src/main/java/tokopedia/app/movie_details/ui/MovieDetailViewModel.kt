package tokopedia.app.movie_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tokopedia.app.abstraction.base.BaseViewModel
import tokopedia.app.abstraction.helper.FetchingIdlingResource
import tokopedia.app.abstraction.util.state.LoaderState
import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.abstraction.util.thread.SchedulerProvider
import tokopedia.app.data.entity.Credits
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.entity.RateMovieParam
import tokopedia.app.data.entity.RateMovieResponse
import tokopedia.app.data.entity.Movies
import tokopedia.app.data.entity.TVShow
import tokopedia.app.movie_details.domain.MovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieDetailContract {
    fun getMovieDetail(movieId: String)
    fun getTVShowDetail(movieId: String)
    fun rateMovie(movieId: String, starRating: Int, guestId: String)
}

class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase,
    dispatcher: SchedulerProvider
) : BaseViewModel(dispatcher), MovieDetailContract {

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    private val _movieCredits = MutableLiveData<Credits>()
    val movieCredits: LiveData<Credits>
        get() = _movieCredits

    private val _tvDetail = MutableLiveData<TVShow>()
    val tvDetail: LiveData<TVShow>
        get() = _tvDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _tvPostRatingStatus = MutableLiveData<RateMovieResponse>()
    val tvPostRatingStatus: LiveData<RateMovieResponse>
        get() = _tvPostRatingStatus

    private val _recommendedMovies = MutableLiveData<Movies>()
    val recommendedMovies: LiveData<Movies>
        get() = _recommendedMovies

    override fun getMovieDetail(movieId: String) {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val workMovieCredit = async { useCase.getMovieCredits(movieId) }
            val workMovieDetail = async { useCase.getMovieDetail(movieId) }

            val resultCredit = workMovieCredit.await()
            val resultDetail = workMovieDetail.await()

            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                _state.value = LoaderState.HideLoading
                when (resultCredit) {
                    is ResultState.Success -> _movieCredits.postValue(resultCredit.data)
                    is ResultState.Error -> _error.postValue(resultCredit.error)
                }

                when (resultDetail) {
                    is ResultState.Success -> {
                        _movieDetail.postValue(resultDetail.data)
                        getMoviesByGenre(resultDetail.data.getAllGenres())
                    }
                    is ResultState.Error -> _error.postValue(resultDetail.error)
                }
            }
        }
    }

    override fun getTVShowDetail(movieId: String) {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getTvDetail(movieId)
            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                _state.value = LoaderState.HideLoading
                when (result) {
                    is ResultState.Success -> _tvDetail.postValue(result.data)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

    override fun rateMovie(movieId: String, starRating: Int, guestId: String) {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.rateMovie(movieId, RateMovieParam(starRating), guestId)
            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                _state.value = LoaderState.HideLoading
                when (result) {
                    is ResultState.Success -> _tvPostRatingStatus.postValue(result.data)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

    private fun getMoviesByGenre(genreIds: String) {
        FetchingIdlingResource.begin()
        launch {
            val result = useCase.getMoviesByGenre(genreIds)
            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                when (result) {
                    is ResultState.Success -> _recommendedMovies.postValue(result.data)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

}
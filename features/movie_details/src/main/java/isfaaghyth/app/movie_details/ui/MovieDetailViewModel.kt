package isfaaghyth.app.movie_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.abstraction.helper.FetchingIdlingResource
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.data.entity.RateMovieParam
import isfaaghyth.app.data.entity.RateMovieResponse
import isfaaghyth.app.data.entity.TVShow
import isfaaghyth.app.movie_details.domain.MovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieDetailContract {
    fun getMovieDetail(movieId: String)
    fun getTVShowDetail(movieId: String)
    fun rateMovie(movieId: String, starRating: Int)
}

class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieDetailUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), MovieDetailContract {

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

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

    override fun getMovieDetail(movieId: String) {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getMovieDetail(movieId)
            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                _state.value = LoaderState.HideLoading
                when (result) {
                    is ResultState.Success -> _movieDetail.postValue(result.data)
                    is ResultState.Error -> _error.postValue(result.error)
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

    override fun rateMovie(movieId: String, starRating: Int) {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.rateMovie(movieId, RateMovieParam(starRating))
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

}
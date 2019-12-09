package isfaaghyth.app.movies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import isfaaghyth.app.abstraction.base.BaseViewModel
import isfaaghyth.app.abstraction.helper.FetchingIdlingResource
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.abstraction.util.state.ResultState
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.abstraction.util.session.GuestSessionResponse
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.movies.domain.GetGuestSessionUseCase
import isfaaghyth.app.movies.domain.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieContract {
    fun getGuestSessionId()
    fun getPopularMovie()
}

class MovieViewModel @Inject constructor(
    private val useCase: MovieUseCase,
    private val guestSessionUseCase: GetGuestSessionUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), MovieContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _result = MutableLiveData<List<Movie>>()
    val result: LiveData<List<Movie>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _guestSessionResult = MutableLiveData<GuestSessionResponse>()
    val guestSessionResult: LiveData<GuestSessionResponse>
        get() = _guestSessionResult

    init {
        getPopularMovie()
    }

    override fun getPopularMovie() {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getPopularMovie()
            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                _state.value = LoaderState.HideLoading
                when (result) {
                    is ResultState.Success -> _result.postValue(result.data.resultsIntent)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

    override fun getGuestSessionId() {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val result = guestSessionUseCase.getGuestSessionId()
            withContext(Dispatchers.Main) {
                FetchingIdlingResource.complete()
                _state.value = LoaderState.HideLoading
                when(result) {
                    is ResultState.Success -> _guestSessionResult.postValue(result.data)
                    is ResultState.Error -> _error.postValue(result.error)
                }
            }
        }
    }

}
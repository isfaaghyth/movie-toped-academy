package tokopedia.app.tvshows.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tokopedia.app.abstraction.base.BaseViewModel
import tokopedia.app.abstraction.helper.FetchingIdlingResource
import tokopedia.app.abstraction.util.state.LoaderState
import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.abstraction.util.thread.SchedulerProvider
import tokopedia.app.data.entity.TVShow
import tokopedia.app.tvshows.domain.TVShowUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface TVShowContract {
    fun getPopularTvShow()
}

class TVShowViewModel @Inject constructor(
    private val useCase: TVShowUseCase,
    dispatcher: SchedulerProvider
): BaseViewModel(dispatcher), TVShowContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _result = MutableLiveData<List<TVShow>>()
    val result: LiveData<List<TVShow>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {
        getPopularTvShow()
    }

    override fun getPopularTvShow() {
        FetchingIdlingResource.begin()
        _state.value = LoaderState.ShowLoading
        launch {
            val result = useCase.getPopularTvShow()
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

}
package tokopedia.app.movie_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tokopedia.app.abstraction.util.viewmodel.ViewModelFactory
import tokopedia.app.abstraction.util.viewmodel.ViewModelKey
import tokopedia.app.movie_details.di.MovieDetailScope

@Module abstract class MovieDetailViewModelModule {

    @MovieDetailScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @MovieDetailScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieDetailViewModel): ViewModel

}
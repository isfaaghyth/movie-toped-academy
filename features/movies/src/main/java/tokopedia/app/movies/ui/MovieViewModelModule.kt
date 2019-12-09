package tokopedia.app.movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tokopedia.app.abstraction.util.viewmodel.ViewModelFactory
import tokopedia.app.abstraction.util.viewmodel.ViewModelKey
import tokopedia.app.movies.di.MovieScope

@Module abstract class MovieViewModelModule {

    @MovieScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

}
package tokopedia.app.tvshows.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tokopedia.app.abstraction.util.viewmodel.ViewModelFactory
import tokopedia.app.abstraction.util.viewmodel.ViewModelKey
import tokopedia.app.tvshows.di.TVShowScope

@Module abstract class TVShowViewModelModule {

    @TVShowScope
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TVShowViewModel::class)
    internal abstract fun bindMovieViewModel(viewModel: TVShowViewModel): ViewModel

}
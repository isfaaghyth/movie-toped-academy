package tokopedia.app.movies.di

import dagger.Component
import tokopedia.app.movies.ui.MovieFragment
import tokopedia.app.movies.ui.MovieModule
import tokopedia.app.movies.ui.MovieViewModelModule

@MovieScope
@Component(modules = [
    MovieModule::class,
    MovieViewModelModule::class
])
interface MovieComponent {
    fun inject(fragment: MovieFragment)
}
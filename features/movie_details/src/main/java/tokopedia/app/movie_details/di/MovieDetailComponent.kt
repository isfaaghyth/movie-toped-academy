package tokopedia.app.movie_details.di

import dagger.Component
import tokopedia.app.movie_details.ui.MovieDetailActivity
import tokopedia.app.movie_details.ui.MovieDetailModule
import tokopedia.app.movie_details.ui.MovieDetailViewModelModule

@MovieDetailScope
@Component(modules = [
    MovieDetailModule::class,
    MovieDetailViewModelModule::class
])
interface MovieDetailComponent {
    fun inject(activity: MovieDetailActivity)
}
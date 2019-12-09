package tokopedia.app.tvshows.di

import dagger.Component
import tokopedia.app.tvshows.ui.TVShowFragment
import tokopedia.app.tvshows.ui.TVShowModule
import tokopedia.app.tvshows.ui.TVShowViewModelModule

@TVShowScope
@Component(modules = [
    TVShowModule::class,
    TVShowViewModelModule::class
])
interface TVShowComponent {
    fun inject(activity: TVShowFragment)
}
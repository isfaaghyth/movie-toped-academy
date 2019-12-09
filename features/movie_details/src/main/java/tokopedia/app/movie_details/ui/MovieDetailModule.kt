package tokopedia.app.movie_details.ui

import dagger.Module
import dagger.Provides
import tokopedia.app.abstraction.util.thread.ApplicationSchedulerProvider
import tokopedia.app.abstraction.util.thread.SchedulerProvider
import tokopedia.app.data.di.DataModule
import tokopedia.app.data.di.DataScope
import tokopedia.app.data.repository.movie_detail.MovieDetailRepository
import tokopedia.app.data.repository.movie_detail.MovieDetailRepositoryImpl
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.movie_details.di.MovieDetailScope
import tokopedia.app.movie_details.domain.MovieDetailUseCase

@Module(includes = [DataModule::class])
class MovieDetailModule {

    @MovieDetailScope @Provides
    fun provideRepository(@DataScope service: NetworkServices): MovieDetailRepository {
        return MovieDetailRepositoryImpl(service)
    }

    @MovieDetailScope @Provides
    fun provideUseCase(repository: MovieDetailRepository): MovieDetailUseCase {
        return MovieDetailUseCase(repository)
    }

    @MovieDetailScope @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

}
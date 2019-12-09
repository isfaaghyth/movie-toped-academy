package tokopedia.app.movies.ui

import dagger.Module
import dagger.Provides
import tokopedia.app.abstraction.util.thread.ApplicationSchedulerProvider
import tokopedia.app.abstraction.util.thread.SchedulerProvider
import tokopedia.app.data.di.DataModule
import tokopedia.app.data.di.DataScope
import tokopedia.app.data.repository.guest_session.GuestSessionRepository
import tokopedia.app.data.repository.guest_session.GuestSessionRepositoryImpl
import tokopedia.app.data.repository.movie.MovieRepository
import tokopedia.app.data.repository.movie.MovieRepositoryImpl
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.movies.di.MovieScope
import tokopedia.app.movies.domain.GetGuestSessionUseCase
import tokopedia.app.movies.domain.MovieUseCase

@Module(includes = [DataModule::class])
class MovieModule {

    @MovieScope
    @Provides
    fun provideRepository(@DataScope service: NetworkServices): MovieRepository {
        return MovieRepositoryImpl(service)
    }

    @MovieScope
    @Provides
    fun provideUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(repository)
    }

    @MovieScope
    @Provides
    fun provideGuestSessionRepository(@DataScope service: NetworkServices): GuestSessionRepository {
        return GuestSessionRepositoryImpl(service)
    }

    @MovieScope
    @Provides
    fun provideGuestSessionUseCase(repository: GuestSessionRepository): GetGuestSessionUseCase {
        return GetGuestSessionUseCase(repository)
    }

    @MovieScope
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

}
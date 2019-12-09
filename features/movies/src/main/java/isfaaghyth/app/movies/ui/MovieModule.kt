package isfaaghyth.app.movies.ui

import dagger.Module
import dagger.Provides
import isfaaghyth.app.abstraction.util.thread.ApplicationSchedulerProvider
import isfaaghyth.app.abstraction.util.thread.SchedulerProvider
import isfaaghyth.app.data.di.DataModule
import isfaaghyth.app.data.di.DataScope
import isfaaghyth.app.data.repository.guest_session.GuestSessionRepository
import isfaaghyth.app.data.repository.guest_session.GuestSessionRepositoryImpl
import isfaaghyth.app.data.repository.movie.MovieRepository
import isfaaghyth.app.data.repository.movie.MovieRepositoryImpl
import isfaaghyth.app.data.routes.NetworkServices
import isfaaghyth.app.movies.di.MovieScope
import isfaaghyth.app.movies.domain.GetGuestSessionUseCase
import isfaaghyth.app.movies.domain.MovieUseCase

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
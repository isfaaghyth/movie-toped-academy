package tokopedia.app.tvshows.ui

import dagger.Module
import dagger.Provides
import tokopedia.app.abstraction.util.thread.ApplicationSchedulerProvider
import tokopedia.app.abstraction.util.thread.SchedulerProvider
import tokopedia.app.data.di.DataModule
import tokopedia.app.data.di.DataScope
import tokopedia.app.data.repository.tvshow.TVShowRepository
import tokopedia.app.data.repository.tvshow.TVShowRepositoryImpl
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.tvshows.di.TVShowScope
import tokopedia.app.tvshows.domain.TVShowUseCase

@Module(includes = [DataModule::class])
class TVShowModule {

    @TVShowScope @Provides
    fun provideRepository(@DataScope service: NetworkServices): TVShowRepository {
        return TVShowRepositoryImpl(service)
    }

    @TVShowScope @Provides
    fun provideUseCase(repository: TVShowRepository): TVShowUseCase {
        return TVShowUseCase(repository)
    }

    @TVShowScope @Provides
    fun provideSchedulerProvider(): SchedulerProvider = ApplicationSchedulerProvider()

}
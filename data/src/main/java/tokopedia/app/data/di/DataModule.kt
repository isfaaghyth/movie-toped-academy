package tokopedia.app.data.di

import dagger.Module
import dagger.Provides
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.network.Network.retrofitClient

@Module class DataModule {

    @Provides @DataScope
    fun provideServices(): NetworkServices {
        return retrofitClient().create(NetworkServices::class.java)
    }

}
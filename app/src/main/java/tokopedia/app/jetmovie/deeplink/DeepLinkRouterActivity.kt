package tokopedia.app.jetmovie.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import tokopedia.app.movie_details.di.MovieDetailDeepLinkModule
import tokopedia.app.movie_details.di.MovieDetailDeepLinkModuleLoader

@DeepLinkHandler(
    AppDeepLinkModule::class,
    MovieDetailDeepLinkModule::class
)
class DeepLinkRouterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(
            AppDeepLinkModuleLoader(),
            MovieDetailDeepLinkModuleLoader())
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
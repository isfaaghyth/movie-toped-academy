package isfaaghyth.app.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("guest_session_id", "a246d82f2d38155a729952c60573f97d")
                /*`guest_session_id` value need to be replaced with session_id generated in
                   https://developers.themoviedb.org/3/authentication/create-guest-session */
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
package tokopedia.app.data.repository.guest_session

import retrofit2.Response
import tokopedia.app.data.entity.GuestSession
import tokopedia.app.data.routes.NetworkServices

class GuestSessionRepositoryImpl constructor(
    private val service: NetworkServices
): GuestSessionRepository {

    override suspend fun getGuestSessionId(): Response<GuestSession> {
        return service.getGuestSessionId()
    }

}
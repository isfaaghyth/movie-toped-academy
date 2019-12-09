package tokopedia.app.data.repository.guest_session

import tokopedia.app.abstraction.util.session.GuestSessionResponse
import tokopedia.app.data.routes.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class GuestSessionRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): GuestSessionRepository {

    override suspend fun getGuestSessionId(): Response<GuestSessionResponse> {
        return service.getGuestSessionId()
    }

}
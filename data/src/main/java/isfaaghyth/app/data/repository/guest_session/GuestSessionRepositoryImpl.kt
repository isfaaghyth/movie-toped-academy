package isfaaghyth.app.data.repository.guest_session

import isfaaghyth.app.abstraction.util.session.GuestSessionResponse
import isfaaghyth.app.data.routes.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class GuestSessionRepositoryImpl @Inject constructor(
    private val service: NetworkServices
): GuestSessionRepository {

    override suspend fun getGuestSessionId(): Response<GuestSessionResponse> {
        return service.getGuestSessionId()
    }

}
package isfaaghyth.app.data.repository.guest_session

import isfaaghyth.app.abstraction.util.session.GuestSessionResponse
import retrofit2.Response

interface GuestSessionRepository {
    suspend fun getGuestSessionId(): Response<GuestSessionResponse>
}
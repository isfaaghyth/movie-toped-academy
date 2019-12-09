package tokopedia.app.data.repository.guest_session

import retrofit2.Response
import tokopedia.app.data.entity.GuestSession

interface GuestSessionRepository {
    suspend fun getGuestSessionId(): Response<GuestSession>
}
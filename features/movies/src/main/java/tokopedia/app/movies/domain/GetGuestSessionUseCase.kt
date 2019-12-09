package tokopedia.app.movies.domain

import tokopedia.app.abstraction.util.UNSUCCESSFUL_MESSAGE
import tokopedia.app.abstraction.util.ext.fetchState
import tokopedia.app.abstraction.util.session.GuestSessionResponse
import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.repository.guest_session.GuestSessionRepository
import javax.inject.Inject

/**
 * @author by jessica on 2019-12-09
 */

class GetGuestSessionUseCase @Inject constructor(private val repository: GuestSessionRepository) {
    suspend fun getGuestSessionId(): ResultState<GuestSessionResponse> {
        return fetchState {
            val response = repository.getGuestSessionId()
            if (response.isSuccessful) ResultState.Success(response.body()!!)
            else ResultState.Error(UNSUCCESSFUL_MESSAGE)
        }
    }
}
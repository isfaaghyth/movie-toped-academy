package tokopedia.app.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author by jessica on 2019-12-02
 */

data class GuestSession(
    @Expose @SerializedName("success") val success: Boolean = false,
    @Expose @SerializedName("guest_session_id") val guestSessionId: String = "",
    @Expose @SerializedName("expires_at") val expiresAt: String = ""
)
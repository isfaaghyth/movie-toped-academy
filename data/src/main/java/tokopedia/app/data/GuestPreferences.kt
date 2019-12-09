package tokopedia.app.data

import android.content.Context
import android.content.SharedPreferences
import tokopedia.app.data.entity.GuestSession
import tokopedia.app.data.mapper.DateMapper
import java.util.*

/**
 * @author by jessica on 2019-12-02
 */

class GuestPreferences(val context: Context) {

    private var sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

    fun sessionExpiredTime(): Date? {
        val expiredAt = sharedPreferences.getString(EXPIRED_KEY, null)
        return DateMapper.stringToDate(DateMapper.DEFAULT_TIMESTAMP_FORMAT, expiredAt?: "")
    }

    fun guestSessionId(): String = sharedPreferences.getString(GUEST_ID_KEY, "") ?: ""

    fun guestSessionId(guestSessionResponse: GuestSession) {
        sharedPreferences.edit().putString(GUEST_ID_KEY, guestSessionResponse.guestSessionId).apply()
        sharedPreferences.edit().putString(EXPIRED_KEY, guestSessionResponse.expiresAt).apply()
    }

    companion object {
        private const val PREFERENCE_KEY = "myPref"
        const val EXPIRED_KEY = "expiredAt"
        const val GUEST_ID_KEY = "guestId"
    }

}
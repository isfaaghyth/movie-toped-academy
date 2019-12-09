package tokopedia.app.abstraction.util.session

import android.content.Context
import android.content.SharedPreferences
import tokopedia.app.abstraction.util.DateUtil
import java.util.*

/**
 * @author by jessica on 2019-12-02
 */

class SessionUtil(val context: Context) {

    var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    fun getSessionExpiredTime(): Date? {
        val expiredAt = sharedPreferences.getString(EXPIRED_KEY, null)
        return if (expiredAt == null) null else DateUtil.stringToDate(DateUtil.DEFAULT_TIMESTAMP_FORMAT, expiredAt)
    }

    fun getGuestSessionId(): String = sharedPreferences.getString(GUEST_ID_KEY, "") ?: ""

    fun saveGuestSessionId(guestSessionResponse: GuestSessionResponse) {
        sharedPreferences.edit().putString(GUEST_ID_KEY, guestSessionResponse.guestSessionId).apply()
        sharedPreferences.edit().putString(EXPIRED_KEY, guestSessionResponse.expiresAt).apply()
    }

    companion object {

        private const val PREFERENCE_KEY = "myPref"
        const val EXPIRED_KEY = "expiredAt"
        const val GUEST_ID_KEY = "guestId"
    }
}
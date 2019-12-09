package tokopedia.app.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author by jessica on 2019-12-02
 */

data class RateMovieResponse(
    @Expose @SerializedName("status_code") val statusCode: Int = 0,
    @Expose @SerializedName("status_message") val statusMessage: String = ""
)
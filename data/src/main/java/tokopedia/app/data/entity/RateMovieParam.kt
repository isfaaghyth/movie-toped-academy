package tokopedia.app.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author by jessica on 2019-12-02
 */

data class RateMovieParam(
    @Expose @SerializedName("value") val value: Int = 0
)
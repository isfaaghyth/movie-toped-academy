package tokopedia.app.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import tokopedia.app.data.BuildConfig

/**
 * @author by furqan on 02/12/2019
 */
data class Credits(
    @SerializedName("id") @Expose val id: Int = 0,
    @SerializedName("cast") @Expose val cast: List<Cast> = listOf(),
    @SerializedName("crew") @Expose val crew: List<Crew> = listOf()
)

data class Cast(
    @SerializedName("cast_id") @Expose val castId: Int = 0,
    @SerializedName("character") @Expose val character: String = "",
    @SerializedName("credit_id") @Expose val creditId: String = "",
    @SerializedName("gender") @Expose val gender: Int = 0,
    @SerializedName("id") @Expose val id: Int = 0,
    @SerializedName("name") @Expose val name: String = "",
    @SerializedName("order") @Expose val order: Int = 0,
    @SerializedName("profile_path") @Expose val profilePath: String = ""
) {
    fun getProfileUrl(): String = "${BuildConfig.IMAGE_URL}$profilePath"
}

data class Crew(
    @SerializedName("department") @Expose val department: String = "",
    @SerializedName("credit_id") @Expose val creditId: String = "",
    @SerializedName("gender") @Expose val gender: Int = 0,
    @SerializedName("id") @Expose val id: Int = 0,
    @SerializedName("name") @Expose val name: String = "",
    @SerializedName("job") @Expose val job: String = "",
    @SerializedName("profile_path") @Expose val profilePath: String = ""
) {
    fun getProfileUrl(): String = "${BuildConfig.IMAGE_URL}$profilePath"
}

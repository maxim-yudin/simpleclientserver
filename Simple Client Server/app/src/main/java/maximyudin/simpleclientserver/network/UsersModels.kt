package maximyudin.simpleclientserver.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersResponse(
    val page: Int,
    @Json(name = "per_page") val perPage: Int,
    val total: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "data") val users: List<User>
) : Parcelable

@Parcelize
data class User(
    val id: String,
    val email: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "avatar") val photoUrl: String
) : Parcelable
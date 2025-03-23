package io.radhika.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("age")
    val age: Int? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,

//    @SerializedName("address")
//    val address: String? = null,
//    @SerializedName("hobby")
//    val hobby: String? = null,
//    @SerializedName("favorite_color")
//    val favoriteColor: String? = null,
//    @SerializedName("married_status")
//    val marriedStatus: Boolean? = null,
//    @SerializedName("confident_level")
//    val confidentLevel: Double? = null,
) {
    companion object
}
// {"first_name":"John","last_name":"Doe","age":30,"phone_number":"+1234567890","address":"123 Main Street, City, Country","hobby":"Reading","favorite_color":"Blue","married_status":false,"confident_level":95.5}
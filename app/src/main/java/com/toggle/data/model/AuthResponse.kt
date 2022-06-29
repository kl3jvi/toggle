package com.toggle.data.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


class AuthResponse : ArrayList<AuthResponseItem>()

@JsonClass(generateAdapter = true)
data class AuthResponseItem(
    @Json(name = "email")
    val email: String,
    @Json(name = "Flag")
    val flag: String,
    @Json(name = "TuserID")
    val tuserID: Int,
    @Json(name = "UserFName")
    val userFName: String,
    @Json(name = "UserId")
    val userId: Int,
    @Json(name = "UserlName")
    val userlName: String
)
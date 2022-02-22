package com.toggle.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class LoginCheck : ArrayList<LoginCheckItem>()

@JsonClass(generateAdapter = true)
data class LoginCheckItem(
    @Json(name = "")
    val firstItem: ArrayList<First>?,
    @Json(name = "")
    val secondItem: Second
)

@JsonClass(generateAdapter = true)
data class First(
    @Json(name = "Flag")
    val flag: String?,
    @Json(name = "UserId")
    val userId: Int?,
    @Json(name = "UserFName")
    val userFName: String?,
    @Json(name = "UserlName")
    val userlName: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "TuserID")
    val tuserID: Int?
)

@JsonClass(generateAdapter = true)
data class Second(
    @Json(name = "affectedRows")
    val affectedRows: Int?,
    @Json(name = "changedRows")
    val changedRows: Int?,
    @Json(name = "fieldCount")
    val fieldCount: Int?,
    @Json(name = "insertId")
    val insertId: Int?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "protocol41")
    val protocol41: Boolean?,
    @Json(name = "serverStatus")
    val serverStatus: Int?,
    @Json(name = "warningCount")
    val warningCount: Int?
)

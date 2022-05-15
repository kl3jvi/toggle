package com.toggle.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LoginResponseItem(
    @Json(name = "affectedRows")
    val affectedRows: Int?,
    @Json(name = "changedRows")
    val changedRows: Int?,
    @Json(name = "fieldCount")
    val fieldCount: Int?,
    @Json(name = "Flag")
    val flag: String?,
    @Json(name = "insertId")
    val insertId: Int?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "protocol41")
    val protocol41: Boolean?,
    @Json(name = "serverStatus")
    val serverStatus: Int?,
    @Json(name = "warningCount")
    val warningCount: Int?,
    @Json(name = "UserId")
    val userId: Int?,
    @Json(name = "userfname")
    val firstName: String?,
    @Json(name = "userlname")
    val lastName: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "TuserID")
    val TUserId: String?
)

package com.toggle.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


//https://api.toggle.com.co:3011/reg/regAndLoginCheckAPP?apiAction=LoginCheckMobile&emailID=admin@clay.co.in&passWord=Falcon@158
//https://api.toggle.com.co:3011/reg/regAndLoginCheck?apiAction=LoginCheckMobile&emailID=admin%40clay.co.in&passWord=Falcon%40158

@JsonClass(generateAdapter = true)
data class ContactDetails(
    @Json(name = "id")
    val id: Int,
    @Json(name = "Name")
    val name: String
)
package com.toggle.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ContactDetails(
    @Json(name = "id")
    val id: Int,
    @Json(name = "Name")
    val name: String
)
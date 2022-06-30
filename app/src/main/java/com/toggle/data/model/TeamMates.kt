package com.toggle.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TeamMatesItem(
    @Json(name = "TeamID")
    val teamID: Int,
    @Json(name = "Teamname")
    val teamName: String
)
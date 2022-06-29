package com.toggle.data.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


class TeamMates : ArrayList<TeamMatesItem>()

@JsonClass(generateAdapter = true)
data class TeamMatesItem(
    @Json(name = "TeamID")
    val teamID: Int,
    @Json(name = "Teamname")
    val teamname: String
)
package com.toggle.domain.repository

import com.toggle.data.model.ContactDetails
import com.toggle.data.model.LoginResponseItem
import com.toggle.data.model.TeamMatesItem

interface PeopleRepository {
    suspend fun getContactDetails(userId: String, agentID: String): List<ContactDetails>
    suspend fun getTeamMates(userId: String, teamUserOrNum: String): List<TeamMatesItem>
}
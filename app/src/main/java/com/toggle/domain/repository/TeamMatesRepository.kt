package com.toggle.domain.repository

import com.toggle.data.model.TeamMatesItem
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TeamMatesRepository {
    fun getTeamMates(userId: String, teamUserOrNum: String): Flow<Resource<List<TeamMatesItem>>>

}

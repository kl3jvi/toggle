package com.toggle.data.repository

import com.toggle.data.network.APIService
import com.toggle.domain.repository.PeopleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val ioDispatcher: CoroutineDispatcher
) : PeopleRepository {

    override suspend fun getContactDetails(userId: String, agentID: String) =
        withContext(ioDispatcher) {
            apiService.getContactDetails(CONTACT_DETAILS_MODE, userId, agentID)
        }

    override suspend fun getTeamMates(userId: String, teamUserOrNum: String) =
        withContext(ioDispatcher) {
            apiService.getTeamMates(TEAM_MATES_MODE, userId, teamUserOrNum)
        }

    companion object {
        const val CONTACT_DETAILS_MODE = "SELECTCONTACTDETAIL"
        const val TEAM_MATES_MODE = "SHOWTEAMSUSERWISE"

    }
}
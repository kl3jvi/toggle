package com.toggle.data.repository

import com.toggle.data.network.APIService
import com.toggle.domain.repository.TeamMatesRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TeamMatesRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val ioDispatcher: CoroutineDispatcher
) : TeamMatesRepository {
    override fun getTeamMates(
        userId: String,
        teamUserOrNum: String
    ) = flow {
        try {
            val result = apiService.getTeamMates(MODE, userId, teamUserOrNum)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failed(e.localizedMessage ?: "Error Occurred!"))
        }
    }.flowOn(ioDispatcher)

    companion object {
        const val MODE = "SHOWTEAMSUSERWISE"
    }
}
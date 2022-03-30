package com.toggle.data.repository

import com.toggle.data.network.AuthenticationService
import com.toggle.domain.repository.CallHistoryRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CallHistoryRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val ioDispatcher: CoroutineDispatcher
) : CallHistoryRepository {
    override fun getCallHistory(userId: String, teamId: String) = flow {
        try {
            val result = authenticationService.getCallHistory(MODE, userId, teamId)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failed(e.localizedMessage ?: "Error Occurred!"))
        }
    }.flowOn(ioDispatcher)

    companion object {
        const val MODE = "SHOWCDRDATAUSERIDAPP"
    }
}
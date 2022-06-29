package com.toggle.data.repository

import com.toggle.data.network.APIService
import com.toggle.domain.repository.PeopleRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val ioDispatcher: CoroutineDispatcher
) : PeopleRepository {


    override fun getContactDetails(userId: String, agentID: String) = flow {
        try {
            val result = apiService.getContactDetails(MODE, userId, agentID)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failed(e.localizedMessage ?: "Error Occurred!"))
        }
    }.flowOn(ioDispatcher)

    companion object {
        const val MODE = "SELECTCONTACTDETAIL"
    }
}
package com.toggle.domain.use_cases

import com.toggle.domain.repository.PeopleRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetContactDetailsUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    operator fun invoke(userId: String, agentID: String) = flow {
        try {
            val result = peopleRepository.getContactDetails(userId, agentID)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Failed(e.localizedMessage ?: "Error Occurred!"))
        }
    }
}
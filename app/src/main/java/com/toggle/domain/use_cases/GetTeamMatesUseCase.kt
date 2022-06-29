package com.toggle.domain.use_cases

import com.toggle.domain.repository.PeopleRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTeamMatesUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    operator fun invoke(userId: String, teamUserOrNum: String) = flow {
        try {
            val result = peopleRepository.getTeamMates(
                userId,
                teamUserOrNum
            )
            emit(Resource.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
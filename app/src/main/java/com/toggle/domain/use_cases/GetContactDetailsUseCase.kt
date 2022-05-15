package com.toggle.domain.use_cases

import com.toggle.data.model.ContactDetails
import com.toggle.domain.repository.PeopleRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContactDetailsUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    operator fun invoke(userId: String, agentID: String): Flow<Resource<List<ContactDetails>>> {
        return peopleRepository.getContactDetails(userId, agentID)
    }
}
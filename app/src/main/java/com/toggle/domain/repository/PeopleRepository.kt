package com.toggle.domain.repository

import com.toggle.data.model.ContactDetails
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getContactDetails(userId: String, agentID: String): Flow<Resource<List<ContactDetails>>>
}
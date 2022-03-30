package com.toggle.domain.repository

import com.toggle.data.model.CallHistoryItem
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CallHistoryRepository {
    fun getCallHistory(userId: String, teamId: String): Flow<Resource<List<CallHistoryItem>>>
}
package com.toggle.domain.use_cases

import com.toggle.data.model.CallHistoryItem
import com.toggle.domain.repository.CallHistoryRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCallHistoryUseCase @Inject constructor(
    private val callRepo: CallHistoryRepository
) {
    operator fun invoke(userId: String, teamId: String): Flow<Resource<List<CallHistoryItem>>> {
        return callRepo.getCallHistory(userId, teamId)
    }
}
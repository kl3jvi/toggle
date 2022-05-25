package com.toggle.ui.fragments.otherFragments.callHistory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toggle.data.model.CallHistoryItem
import com.toggle.domain.repository.LocalStorage
import com.toggle.domain.use_cases.GetCallHistoryUseCase
import com.toggle.utils.Resource
import com.toggle.utils.launchOnIo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CallHistoryViewModel @Inject constructor(
    private val callHistoryUseCase: GetCallHistoryUseCase,
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _callHistory = MutableStateFlow<List<CallHistoryItem>>(emptyList())
    val callHistory = _callHistory.asStateFlow()
    private val TAG = "TodoViewModel"

    init {
        getCallHistory()
    }

    private fun getCallHistory() {
        viewModelScope.launchOnIo {
            callHistoryUseCase(
                localStorage.userId.toString(),
                localStorage.tUserId.toString()
            ).collect {
                when (it) {
                    is Resource.Failed -> {
                        Log.e(TAG, "getCallHistory:${it.message} ")
                    }
                    is Resource.Success -> _callHistory.value = it.data
                }
            }
        }
    }
}

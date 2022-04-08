package com.toggle.ui.fragments.otherFragments.callHistory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toggle.data.model.CallHistoryItem
import com.toggle.domain.use_cases.GetCallHistoryUseCase
import com.toggle.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallHistoryViewModel @Inject constructor(
    private val callHistoryUseCase: GetCallHistoryUseCase
) : ViewModel() {

    private val _callHistory = MutableStateFlow<List<CallHistoryItem>>(emptyList())
    val callHistory = _callHistory.asStateFlow()
    private val TAG = "TodoViewModel"

    init {
        getCallHistory()
    }

    private fun getCallHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            callHistoryUseCase("144079", "390").collect {
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

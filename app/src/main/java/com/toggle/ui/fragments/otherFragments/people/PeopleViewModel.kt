package com.toggle.ui.fragments.otherFragments.people

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toggle.data.model.ContactDetails
import com.toggle.data.model.TeamMatesItem
import com.toggle.domain.repository.LocalStorage
import com.toggle.domain.use_cases.GetContactDetailsUseCase
import com.toggle.domain.use_cases.GetTeamMatesUseCase
import com.toggle.utils.Resource
import com.toggle.utils.launchOnIo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getContactDetailsUseCase: GetContactDetailsUseCase,
    private val getTeamMatesUseCase: GetTeamMatesUseCase,
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _contactDetails = MutableStateFlow<List<ContactDetails>>(emptyList())
    val contactDetails = _contactDetails.asStateFlow()


    private val _teamMates = MutableStateFlow<List<TeamMatesItem>>(emptyList())
    val teamMates = _teamMates.asStateFlow()
    private val TAG = "PeopleViewModel"

    init {
        getContactDetails()
        getTeamMates()
    }

    private fun getTeamMates() {
        viewModelScope.launchOnIo {
            getTeamMatesUseCase(
                819361.toString(),
                403.toString()
            ).collect {
                when (it) {
                    is Resource.Failed -> {
                        Log.e(TAG, "getTeamMates:${it.message} ")
                    }
                    is Resource.Success -> {
                        _teamMates.value = it.data
                    }
                }
            }
        }
    }

    private fun getContactDetails() {
        viewModelScope.launchOnIo {
            getContactDetailsUseCase(
                /*localStorage.userId.toString()*/290804.toString(),
                /*localStorage.tUserId.toString()*/ 359.toString()
            ).collect {
                when (it) {
                    is Resource.Failed -> {
                        Log.e(TAG, "getCallHistory:${it.message} ")
                    }
                    is Resource.Success -> {
                        _contactDetails.value = it.data
                    }
                }
            }
        }
    }
}
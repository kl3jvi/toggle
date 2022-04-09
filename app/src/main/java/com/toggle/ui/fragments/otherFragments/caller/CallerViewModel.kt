package com.toggle.ui.fragments.otherFragments.caller

import androidx.lifecycle.ViewModel
import com.toggle.data.repository.LocalStorageImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CallerViewModel @Inject constructor(
    private val localStorage: LocalStorageImpl
) : ViewModel() {
    fun getUserSipId() = localStorage.sipId
}
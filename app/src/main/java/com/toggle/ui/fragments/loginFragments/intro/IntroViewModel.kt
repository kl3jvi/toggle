package com.toggle.ui.fragments.loginFragments.intro

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.toggle.domain.repository.LocalStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {
    fun isUserLoggedIn(): Boolean {
        Log.e(TAG, "isUserLoggedIn: ${localStorage.sipId} ")
        return localStorage.sipId != null
    }
}
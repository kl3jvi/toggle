package com.toggle.ui.fragments.loginFragments.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toggle.domain.use_cases.CheckLoginUseCase
import com.toggle.utils.launchOnIo
import com.toggle.utils.mapToState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkLoginUseCase: CheckLoginUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun checkForLogIn(email: String, password: String) =
        checkLoginUseCase(email, password).mapToState()

    private val

}
package com.toggle.ui.fragments.loginFragments.login

import androidx.lifecycle.ViewModel
import com.toggle.domain.use_cases.CheckLoginUseCase
import com.toggle.utils.mapToState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkLoginUseCase: CheckLoginUseCase
) : ViewModel() {

    fun checkForLogIn(email: String, password: String) =
        checkLoginUseCase(email, password).mapToState()


}
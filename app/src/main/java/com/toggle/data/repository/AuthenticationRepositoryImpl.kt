package com.toggle.data.repository


import com.toggle.data.model.LoginResponseItem
import com.toggle.data.network.AuthenticationService
import com.toggle.domain.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {
    override suspend fun checkLogin(email: String, password: String): List<LoginResponseItem> {
        return withContext(ioDispatcher) {
            authenticationService.login(LOGIN_API_ACTION, email, password)
        }
    }

    companion object {
        const val LOGIN_API_ACTION = "LoginCheckMobile"
    }
}
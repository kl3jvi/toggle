package com.toggle.data.repository


import com.toggle.data.model.LoginResponseItem
import com.toggle.data.network.APIService
import com.toggle.domain.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val apiService: APIService,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationRepository {
    override suspend fun checkLogin(email: String, password: String): List<LoginResponseItem> {
        return withContext(ioDispatcher) {
            apiService.login(LOGIN_API_ACTION, email, password)
        }
    }

    companion object {
        const val LOGIN_API_ACTION = "LoginCheckMobile"
    }
}
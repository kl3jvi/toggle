package com.toggle.data.repository

import com.toggle.data.model.LoginCheck
import com.toggle.data.model.LoginCheckItem
import com.toggle.data.network.AuthenticationService
import com.toggle.domain.repository.AuthenticationRepository
import com.toggle.domain.repository.NetworkBoundRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationService: AuthenticationService
) : AuthenticationRepository {
    override fun checkLogin(email: String, password: String): Flow<Resource<List<LoginCheckItem>>> {
        return object : NetworkBoundRepository<List<LoginCheckItem>>() {
            override suspend fun fetchFromRemote(): Response<List<LoginCheckItem>> {
                return authenticationService.checkLogin(API_ACTION, email, password)
            }
        }.asFlow()
    }

    companion object {
        const val API_ACTION = "LoginCheckMobile"
    }
}
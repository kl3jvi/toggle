package com.toggle.data.repository

import com.toggle.data.model.First
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
    override fun checkLogin(email: String, password: String): Flow<Resource<First>> {
        return object : NetworkBoundRepository<First>() {
            override suspend fun fetchFromRemote(): Response<First> {
                return authenticationService.checkLogin(API_ACTION, email, password)
            }
        }.asFlow()
    }

    companion object {
        const val API_ACTION = "LoginCheckMobile"
    }
}
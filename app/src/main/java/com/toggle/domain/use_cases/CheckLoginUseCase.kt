package com.toggle.domain.use_cases

import com.toggle.data.model.First
import com.toggle.data.model.LoginCheckItem
import com.toggle.data.repository.AuthenticationRepositoryImpl
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val authenticationRepositoryImpl: AuthenticationRepositoryImpl
) {
    operator fun invoke(email: String, password: String): Flow<Resource<First>> {
        return authenticationRepositoryImpl.checkLogin(email, password)
    }
}
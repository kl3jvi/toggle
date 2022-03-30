package com.toggle.domain.use_cases

import com.toggle.data.repository.AuthenticationRepositoryImpl
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val authenticationRepositoryImpl: AuthenticationRepositoryImpl
) {
    operator fun invoke(email: String, password: String) = flow {
        try {
            val result = authenticationRepositoryImpl.checkLogin(email, password)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
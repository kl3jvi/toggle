package com.toggle.domain.use_cases

import com.toggle.domain.repository.AuthenticationRepository
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) = flow {
        try {
            val result = authenticationRepository.checkLogin(email, password)
            emit(Resource.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Failed(e.message.toString()))
        }
    }
}
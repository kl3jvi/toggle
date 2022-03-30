package com.toggle.domain.repository

import com.toggle.data.model.LoginResponseItem

interface AuthenticationRepository {
    suspend fun checkLogin(email: String, password: String): List<LoginResponseItem>?
}
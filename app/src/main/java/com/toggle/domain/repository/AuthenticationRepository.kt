package com.toggle.domain.repository

import com.toggle.data.model.LoginCheck
import com.toggle.data.model.LoginCheckItem
import com.toggle.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun checkLogin(email: String, password: String): Flow<Resource<List<LoginCheckItem>>>
}
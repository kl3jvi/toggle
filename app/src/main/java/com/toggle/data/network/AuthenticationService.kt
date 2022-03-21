package com.toggle.data.network

import com.toggle.data.model.First
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthenticationService {

    @GET("reg/regAndLoginCheck")
    suspend fun checkLogin(
        @Query("apiAction") apiAction: String,
        @Query("emailID") email: String,
        @Query("passWord") password: String,
    ): Response<First>


}
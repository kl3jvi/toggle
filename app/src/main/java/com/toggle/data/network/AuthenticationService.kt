package com.toggle.data.network

import com.toggle.data.model.CallHistoryItem
import com.toggle.data.model.ContactDetails
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthenticationService {

    @GET("reg/regAndLoginCheck")
    suspend fun checkLogin(
        @Query("apiAction") apiAction: String,
        @Query("emailID") email: String,
        @Query("passWord") password: String,
    ): ResponseBody

    @GET("reg/userCalldataApp")
    suspend fun getCallHistory(
        @Query("Mode") apiAction: String,
        @Query("userId") email: String,
        @Query("TeamID") password: String,
    ): List<CallHistoryItem>


    @GET("reg/saveNumberdetailInAPPnew")
    suspend fun getContactDetails(
        @Query("Mode") apiAction: String,
        @Query("userId") email: String,
        @Query("agentID") password: String,
    ): List<ContactDetails>


}
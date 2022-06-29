package com.toggle.data.network

import com.toggle.data.model.CallHistoryItem
import com.toggle.data.model.ContactDetails
import com.toggle.data.model.LoginResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthenticationService {

    @GET("reg/regAndLoginCheckAPP")
    suspend fun login(
        @Query("apiAction") apiAction: String,
        @Query("emailID") email: String,
        @Query("passWord") password: String,
    ): List<LoginResponseItem>

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
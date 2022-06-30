package com.toggle.data.network

import com.toggle.data.model.CallHistoryItem
import com.toggle.data.model.ContactDetails
import com.toggle.data.model.LoginResponseItem
import com.toggle.data.model.TeamMatesItem
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

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
        @Query("userId") userId: String,
        @Query("agentID") agentId: String,
    ): List<ContactDetails>

    // https://api.toggle.com.co:3011/reg/createTeamUserAPP?Mode=SHOWTEAMSUSERWISE&UserID=819361&TeamUserOrNum=403
    @GET("reg/createTeamUserAPP")
    suspend fun getTeamMates(
        @Query("Mode") apiAction: String,
        @Query("UserID") userId: String,
        @Query("TeamUserOrNum") teamUserOrNum: String,
    ): List<TeamMatesItem>


}
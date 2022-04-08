package com.toggle.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class CallHistoryItem(
    @Json(name = "callDate")
    val callDate: String?,
    @Json(name = "CallFrom")
    val callFrom: String?,
    @Json(name = "callStatus")
    val callStatus: String?,
    @Json(name = "CallingNo")
    val callingNo: String?,
    @Json(name = "callredBY")
    val callredBY: String?,
    @Json(name = "Ctype")
    val ctype: String?,
    @Json(name = "Duration")
    val duration: Int?,
    @Json(name = "recFile")
    val recFile: String?,
    @Json(name = "uniID")
    val uniID: Int?
) : Parcelable
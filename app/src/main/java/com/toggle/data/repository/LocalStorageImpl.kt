package com.toggle.data.repository

import android.content.SharedPreferences
import com.toggle.domain.repository.LocalStorage
import javax.inject.Inject

class LocalStorageImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalStorage {

    companion object {
        const val USER_ID = "userId"
        const val T_USER_ID = "tUserId"
        const val SIP_ID = "sip_Id"
    }

    override var userId: String?
        get() = getData(USER_ID)
        set(value) {
            setData(USER_ID, value)
        }

    override var tUserId: String?
        get() = getData(T_USER_ID)
        set(value) {
            setData(T_USER_ID, value)
        }

    override var sipId: String?
        get() = getData(SIP_ID)
        set(value) {
            setData(SIP_ID, value)
        }

    private fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    private fun setData(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun clearStorage() {
        sharedPreferences.edit().clear().apply()
    }


}
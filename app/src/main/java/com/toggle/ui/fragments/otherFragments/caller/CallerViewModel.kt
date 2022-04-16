package com.toggle.ui.fragments.otherFragments.caller

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toggle.data.repository.LocalStorageImpl
import com.toggle.services.MyAccount
import com.toggle.services.MyCall
import com.toggle.utils.PROXY
import com.toggle.utils.launchOnIo
import dagger.hilt.android.lifecycle.HiltViewModel
import org.pjsip.pjsua2.*
import javax.inject.Inject

@HiltViewModel
class CallerViewModel @Inject constructor(
    localStorage: LocalStorageImpl,
    private val endpoint: Endpoint
) : ViewModel() {
    private val userSipId = localStorage.sipId


    private fun getAccount(): MyAccount? {
        val sipTpConfig = TransportConfig()
        sipTpConfig.port = 5060

        endpoint.transportCreate(
            pjsip_transport_type_e.PJSIP_TRANSPORT_UDP,
            sipTpConfig
        )
        val accountConfiguration = AccountConfig().apply {
            idUri = "sip:$userSipId@pbx.toggle.com.co:5060"
            natConfig.iceEnabled = true
            sipConfig.authCreds.add(AuthCredInfo("Digest", "*", userSipId, 0, userSipId))
            sipConfig.proxies.add(PROXY)
        }
        var acc: MyAccount? = MyAccount(endpoint)

        try {
            acc?.create(accountConfiguration)
        } catch (e: Exception) {
            acc = null
            e.printStackTrace()
            return null
        }
        return acc
    }

    fun makeCall() {
        viewModelScope.launchOnIo {
            checkThread()
            getAccount()?.let {
                endpoint.audDevManager()
                val buddyUri = "sip:09818842864@pbx.toggle.com.co:5060"
                val call = MyCall(it, -1, endpoint)
                val prm = CallOpParam(true)
                prm.opt.flag = pjsua_call_vid_strm_op.PJSUA_CALL_VID_STRM_REMOVE.toLong()
                try {
                    call.makeCall(buddyUri, prm)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    @Synchronized
    fun checkThread() {
        try {
            if (!endpoint.libIsThreadRegistered()) endpoint.libRegisterThread(
                Thread.currentThread().name
            )
        } catch (e: java.lang.Exception) {
            Log.w("SIP", "Threading: libRegisterThread failed: " + e.message)
        }
    }
}


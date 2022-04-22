package com.toggle.ui.fragments.otherFragments.caller

import androidx.lifecycle.ViewModel
import com.toggle.data.repository.LocalStorageImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CallerViewModel @Inject constructor(
    localStorage: LocalStorageImpl,
//    private val endpoint: Endpoint
) : ViewModel() {
    val userSipId = localStorage.sipId


//    fun makeCall() {
//        viewModelScope.launchOnIo {
//            checkThread()
//            getAccount()?.let {
//                endpoint.audDevManager()
//                val buddyUri = "sip:09818842864@pbx.toggle.com.co:5061"
//                val call = MyCall(it, -1, endpoint)
//                val prm = CallOpParam(true)
//                try {
//                    call.makeCall(buddyUri, prm)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }

//    @Synchronized
//    fun checkThread() {
//        try {
//            if (!endpoint.libIsThreadRegistered()) endpoint.libRegisterThread(
//                Thread.currentThread().name
//            )
//        } catch (e: java.lang.Exception) {
//            Log.w("SIP", "Threading: libRegisterThread failed: " + e.message)
//        }
//    }
}


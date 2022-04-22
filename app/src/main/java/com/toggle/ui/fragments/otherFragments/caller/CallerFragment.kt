package com.toggle.ui.fragments.otherFragments.caller

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.databinding.CallerFragmentBinding
import com.toggle.utils.HOST
import com.toggle.utils.NetworkUtils.isConnectedToInternet
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import net.gotev.sipservice.SipAccountData
import net.gotev.sipservice.SipServiceCommand

import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
@AndroidEntryPoint
class CallerFragment : Fragment(R.layout.caller_fragment) {

    private val viewModel: CallerViewModel by viewModels()
    private val binding: CallerFragmentBinding by viewBinding()
//    private val app = MyApp()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.dialer.withModels { buildDial(binding.callNumber) }
        binding.callButton.setOnClickListener {
            if (binding.callNumber.text.isNotEmpty() && binding.callNumber.text.length < 5)
                makeCallWithPermissionCheck("sip:09818842864@pbx.toggle.com.co:5061")
        }
        login()
    }

    //    09818842864
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }


    lateinit var mAccount: SipAccountData
    lateinit var mAccountId: String

    fun login() {
        val psipId = "288636334"
        mAccount = SipAccountData()
        mAccount.host = HOST
        mAccount.realm = "*" //realm指的是：sip:1004@192.168.2.243中的192.168.2.243
        mAccount.port = 5061
        mAccount.username = psipId
        mAccount.password = psipId
        mAccountId = SipServiceCommand.setAccount(requireContext(), mAccount)
        Log.i("MainActivity.TAG", "login: $mAccountId")
    }

    @NeedsPermission(Manifest.permission.RECORD_AUDIO)
    fun makeCall(callNumber: String) {
        try {
            SipServiceCommand.makeCall(requireContext(), mAccountId, callNumber, false, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        handleNetworkState()
    }

    private fun handleNetworkState() {
        isConnectedToInternet(viewLifecycleOwner) { isConnected ->
//            if (!isConnected) MyApp().handleNetworkChange()
        }
    }

}
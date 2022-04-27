package com.toggle.ui.fragments.otherFragments.caller

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
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

    private lateinit var mAccount: SipAccountData
    private lateinit var mAccountId: String

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
                makeCallWithPermissionCheck("sip:${binding.callNumber.text}")
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

    fun login() {
        val pjsipId = "804891331"
        mAccount = SipAccountData()
        mAccount.host = HOST
        mAccount.realm = "*"
        mAccount.port = 5060
        mAccount.username = pjsipId
        mAccount.password = pjsipId
        mAccount.isTcpTransport = false
        mAccountId = SipServiceCommand.setAccount(requireContext(), mAccount)
        Log.i("MainActivity.TAG", "login: $mAccountId")
    }

    @NeedsPermission(Manifest.permission.RECORD_AUDIO)
    fun makeCall(callNumber: String) {
        try {
            SipServiceCommand.makeCall(
                requireContext(),
                mAccountId,
                "sip:09818842864@$HOST",
                false,
                false
            )
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
            if (!isConnected)
                showSnack("Network Error!")
        }
    }

    private fun showSnack(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
//        SipServiceCommand.removeAccount(requireContext(), mAccountId)
    }

}
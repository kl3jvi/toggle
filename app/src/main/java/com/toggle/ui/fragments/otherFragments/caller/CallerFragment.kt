package com.toggle.ui.fragments.otherFragments.caller

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.databinding.CallerFragmentBinding
import com.toggle.utils.NetworkUtils.isConnectedToInternet
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
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
            makeCallWithPermissionCheck()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }


    @NeedsPermission(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA)
    fun makeCall() = viewModel.makeCall()


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
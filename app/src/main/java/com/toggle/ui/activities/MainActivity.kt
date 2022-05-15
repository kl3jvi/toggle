package com.toggle.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.toggle.R
import com.toggle.databinding.ActivityMainBinding
import com.toggle.utils.hide
import com.toggle.utils.show
import dagger.hilt.android.AndroidEntryPoint
import io.karn.notify.Notify
import net.gotev.sipservice.BroadcastEventReceiver
import net.gotev.sipservice.Logger
import org.pjsip.pjsua2.pjsip_status_code


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        Logger.setLogLevel(Logger.LogLevel.DEBUG)
        mReceiver.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mReceiver.unregister(this)
    }

    fun hideBottomNavBar() {
        binding.navView.animate().translationY(binding.navView.height.toFloat()).run {
            duration = 500
            binding.navView.hide()
        }
    }

    fun showBottomNavBar() {
        binding.navView.show()
        binding.navView.animate().translationY(0f).duration = 500
    }


    private var mReceiver: BroadcastEventReceiver = object : BroadcastEventReceiver() {
        override fun onRegistration(accountID: String, registrationStateCode: Int) {
            super.onRegistration(accountID, registrationStateCode)
            Log.i("MainActivity.TAG", "onRegistration: $registrationStateCode ")
            if (registrationStateCode == pjsip_status_code.PJSIP_SC_OK) {
                Toast.makeText(
                    receiverContext,
                    "Account Logged In successfully：$accountID",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    receiverContext,
                    "Login Failed, code：$registrationStateCode", Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onIncomingCall(
            accountID: String,
            callID: Int,
            displayName: String,
            remoteUri: String,
            isVideo: Boolean
        ) {
            super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo)
            Log.e("Incoming call", "---------$accountID")
            val channelKey = "highPriority"
            Notify.with(receiverContext)
                .content { // this: Payload.Content.Default
                    title = displayName
                    text = "Is calling you."
                }.alerting(channelKey) {
                    // Set (notification) channel importance to one of the IMPORTANCE constants.
                    channelImportance = Notify.IMPORTANCE_MAX
                }
                .show()

            CallActivity().startActivityIn(
                receiverContext,
                accountID,
                callID,
                displayName,
                remoteUri,
                isVideo
            )
        }

        override fun onOutgoingCall(
            accountID: String?,
            callID: Int,
            number: String?,
            isVideo: Boolean,
            isVideoConference: Boolean,
            isTransfer: Boolean
        ) {
            super.onOutgoingCall(accountID, callID, number, isVideo, isVideoConference, isTransfer)
            Log.e("Outgoing call", "---------$callID")
            CallActivity().startActivityOut(
                receiverContext,
                accountID,
                callID,
                number,
                isVideo,
                isVideoConference
            )

        }
    }

}
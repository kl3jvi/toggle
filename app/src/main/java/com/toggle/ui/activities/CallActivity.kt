package com.toggle.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.toggle.R
import com.toggle.databinding.ActivityCallBinding
import net.gotev.sipservice.BroadcastEventReceiver
import net.gotev.sipservice.CodecPriority
import net.gotev.sipservice.RtpStreamStats
import net.gotev.sipservice.SipServiceCommand
import org.pjsip.pjsua2.pjsip_inv_state

class CallActivity : AppCompatActivity(),View.OnClickListener {

    private var mAccountID: String? = null
    private var mDisplayName: String? = null
    private var mRemoteUri: String? = null
    private var mCallID = 0
    private var mIsVideo = false
    private var mType = 0
    private var mNumber: String? = null
    private var mIsVideoConference = false
    private var micMute = false

    private lateinit var binding: ActivityCallBinding


    companion object {
        const val TYPE_INCOMING_CALL = 646
        const val TYPE_OUT_CALL = 647
        const val TYPE_CALL_CONNECTED = 648
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver()
        initData()
        binding.btnCancel.setOnClickListener(this)
        binding.buttonAccept.setOnClickListener(this)
        binding.buttonHangup.setOnClickListener(this)

    }

    private fun registerReceiver() {
        mReceiver.register(this)
    }

    private fun initData() {
        mAccountID = intent.getStringExtra("accountID")
        mCallID = intent.getIntExtra("callID", -1)
        mType = intent.getIntExtra("type", -1)
        mDisplayName = intent.getStringExtra("displayName")
        mRemoteUri = intent.getStringExtra("remoteUri")
        mNumber = intent.getStringExtra("number")
        mIsVideo = intent.getBooleanExtra("isVideo", false)
        mIsVideoConference = intent.getBooleanExtra("isVideoConference", false)
        showLayout(mType)
        binding.textViewPeer.text = String.format("%s\n%s", mRemoteUri, mDisplayName)
        binding.tvOutCallInfo.text = String.format("您正在呼叫 %s", mNumber)
        val holder: SurfaceHolder = binding.svLocal.holder
//        holder.addCallback(this)
        binding.svRemote.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {}
            override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
                SipServiceCommand.setupIncomingVideoFeed(
                    this@CallActivity,
                    mAccountID,
                    mCallID,
                    surfaceHolder.surface
                )
            }

            override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
                SipServiceCommand.setupIncomingVideoFeed(
                    this@CallActivity,
                    mAccountID,
                    mCallID,
                    null
                )
            }
        })
    }

    fun startActivityIn(
        context: Context,
        accountID: String?,
        callID: Int,
        displayName: String?,
        remoteUri: String?,
        isVideo: Boolean
    ) {
        val intent = Intent(context, CallActivity::class.java)
        intent.putExtra("accountID", accountID)
        intent.putExtra("callID", callID)
        intent.putExtra("displayName", displayName)
        intent.putExtra("remoteUri", remoteUri)
        intent.putExtra("isVideo", isVideo)
        intent.putExtra("type", TYPE_INCOMING_CALL)
        context.startActivity(intent)
    }

    fun startActivityOut(
        context: Context,
        accountID: String?,
        callID: Int,
        number: String?,
        isVideo: Boolean,
        isVideoConference: Boolean
    ) {
        val intent = Intent(context, CallActivity::class.java)
        intent.putExtra("accountID", accountID)
        intent.putExtra("callID", callID)
        intent.putExtra("number", number)
        intent.putExtra("isVideo", isVideo)
        intent.putExtra("isVideoConference", isVideoConference)
        intent.putExtra("type", TYPE_OUT_CALL)
        context.startActivity(intent)
    }

    //    @OnClick([R.id.buttonAccept, R.id.buttonHangup, R.id.btnCancel, R.id.btnMuteMic, R.id.btnHangUp, R.id.btnSwitchCamera])
    fun onViewClicked(view: View) {

    }

    private fun showLayout(type: Int) {
        when (type) {
            TYPE_INCOMING_CALL -> {
                binding.layoutIncomingCall.visibility = View.VISIBLE
                binding.layoutOutCall.visibility = View.GONE
                binding.layoutConnected.visibility = View.GONE
            }
            TYPE_OUT_CALL -> {
                binding.layoutIncomingCall.setVisibility(View.GONE)
                binding.layoutOutCall.setVisibility(View.VISIBLE)
                binding.layoutConnected.setVisibility(View.GONE)
            }
            TYPE_CALL_CONNECTED -> {
                binding.layoutIncomingCall.visibility = View.GONE
                binding.layoutOutCall.visibility = View.GONE
                binding.layoutConnected.visibility = View.VISIBLE
            }
            else -> {
                val textView = TextView(this)
                textView.text = "ERROR~~~~~~~~~~~~~"
                binding.parent.addView(textView)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mReceiver.unregister(this)
    }

    fun surfaceCreated(surfaceHolder: SurfaceHolder?) {
        SipServiceCommand.startVideoPreview(
            this@CallActivity,
            mAccountID,
            mCallID,
            binding.svLocal.holder.surface
        )
    }

    fun surfaceChanged(surfaceHolder: SurfaceHolder?, i: Int, i1: Int, i2: Int) {}

    fun surfaceDestroyed(surfaceHolder: SurfaceHolder?) {}

    var mReceiver: BroadcastEventReceiver = object : BroadcastEventReceiver() {
        override fun onIncomingCall(
            accountID: String,
            callID: Int,
            displayName: String,
            remoteUri: String,
            isVideo: Boolean
        ) {
            super.onIncomingCall(accountID, callID, displayName, remoteUri, isVideo)
            Toast.makeText(
                receiverContext,
                String.format("收到 [%s] 的来电", remoteUri),
                Toast.LENGTH_SHORT
            ).show()
        }



        override fun onCallState(
            accountID: String?,
            callID: Int,
            callStateCode: Int,
            callStatusCode: Int,
            connectTimestamp: Long
        ) {
            super.onCallState(accountID, callID, callStateCode, callStatusCode, connectTimestamp)
            when {
                pjsip_inv_state.PJSIP_INV_STATE_CALLING.equals(callStateCode) -> {
                    //呼出
                    binding.textViewCallState.text = "calling"
                }
                pjsip_inv_state.PJSIP_INV_STATE_INCOMING.equals(callStateCode) -> {
                    //来电
                    binding.textViewCallState.text = "incoming"
                }
                pjsip_inv_state.PJSIP_INV_STATE_EARLY.equals(callStateCode) -> {
                    //响铃
                    binding.textViewCallState.text = "early"
                }
                pjsip_inv_state.PJSIP_INV_STATE_CONNECTING.equals(callStateCode) -> {
                    //连接中
                    binding.textViewCallState.text = "connecting"
                }
                pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED.equals(callStateCode) -> {
                    //连接成功
                    binding.textViewCallState.text = "confirmed"
                    showLayout(TYPE_CALL_CONNECTED)
                }
                pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED.equals(callStateCode) -> {
                    //断开连接
                    finish()
                }
                pjsip_inv_state.PJSIP_INV_STATE_NULL.equals(callStateCode) -> {
                    //未知错误
                    Toast.makeText(receiverContext, "null", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
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
        }
//

        override fun onStackStatus(started: Boolean) {
            super.onStackStatus(started)
        }

        override fun onReceivedCodecPriorities(codecPriorities: ArrayList<CodecPriority>) {
            super.onReceivedCodecPriorities(codecPriorities)
        }

        override fun onCodecPrioritiesSetStatus(success: Boolean) {
            super.onCodecPrioritiesSetStatus(success)
        }

        override fun onMissedCall(displayName: String, uri: String) {
            super.onMissedCall(displayName, uri)
        }

        override fun onVideoSize(width: Int, height: Int) {
            super.onVideoSize(width, height)
        }


        override fun onCallStats(
            callID: Int,
            duration: Int,
            audioCodec: String?,
            callStatusCode: Int,
            rx: RtpStreamStats?,
            tx: RtpStreamStats?
        ) {
            super.onCallStats(callID, duration, audioCodec, callStatusCode, rx, tx)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonAccept ->                 //接听
                SipServiceCommand.acceptIncomingCall(this, mAccountID, mCallID, mIsVideo)
            R.id.buttonHangup -> {
                //拒绝
                SipServiceCommand.declineIncomingCall(this, mAccountID, mCallID)
                finish()
            }
            R.id.btnCancel -> {
                //取消呼叫
                SipServiceCommand.hangUpActiveCalls(this, mAccountID)
                finish()
            }
            R.id.btnMuteMic -> {
                //麦克风静音
                micMute = !micMute
                SipServiceCommand.setCallMute(this, mAccountID, mCallID, micMute)
                binding.btnMuteMic.isSelected = micMute
            }
            R.id.btnHangUp -> {
                //挂断
                SipServiceCommand.hangUpCall(this, mAccountID, mCallID)
                finish()
            }
            R.id.btnSwitchCamera ->                 //切换摄像头
                SipServiceCommand.switchVideoCaptureDevice(this, mAccountID, mCallID)
        }
    }
}
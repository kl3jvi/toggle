package com.toggle.ui.fragments.otherFragments.caller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.toggle.R
import com.toggle.databinding.CallerFragmentBinding
import com.toggle.dialButton
import com.toggle.services.Global
import com.toggle.services.MyCall
import com.toggle.services.MyLogWriter
import com.toggle.utils.PROXY
import com.toggle.utils.REGISTRAR
import com.toggle.utils.SIP_URI
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pjsip.pjsua2.*

@AndroidEntryPoint
class CallerFragment : Fragment(R.layout.caller_fragment) {
    private val stringList = listOf(
        "A B C",
        "D E F",
        "G H I",
        "J K L",
        "M N O",
        "P Q R S",
        "T U V",
        "W X Y Z",
    )
    val lastRow = listOf(
        "*",
        "0",
        "#"
    )
    private val viewModel: CallerViewModel by viewModels()
    private val binding: CallerFragmentBinding by viewBinding()

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch(Dispatchers.Default) {
            if (Global.endpoint.libGetState() > pjsua_state.PJSUA_STATE_NULL)
                return@launch

            val epConfig = EpConfig()

            /* Setup our log writer */
            val logCfg = epConfig.logConfig
            Global.logWriter = MyLogWriter()
            logCfg.writer = Global.logWriter
            logCfg.decor = logCfg.decor and
                    (pj_log_decoration.PJ_LOG_HAS_CR or
                            pj_log_decoration.PJ_LOG_HAS_NEWLINE).inv().toLong()

            /* Create & init PJSUA2 */
            try {
                Global.endpoint.libCreate()
                Global.endpoint.libInit(epConfig)
            } catch (e: Exception) {
                println(e)
            }


            /* Start PJSUA2 */
            try {
                Global.endpoint.libStart()
            } catch (e: Exception) {
                println(e)
            }
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        /* Setup Start button */
        binding.buttonStart.setOnClickListener {
            setupCall()
        }

        /* Setup Call button */
        binding.buttonCall.setOnClickListener {
            if (Global.endpoint.libGetState() != pjsua_state.PJSUA_STATE_RUNNING)
                return@setOnClickListener

            try {
                /* Setup null audio (good for emulator) */
                Global.endpoint.audDevManager().setNullDev()

                /* Make call (to itself) */
                val call = MyCall(Global.account, -1)
                call.makeCall(SIP_URI, CallOpParam(true))
            } catch (e: Exception) {
                println(e)
            }
        }

    }

    private fun initViews() {
        binding.dialer.withModels {

            for (i in 1..12) {
                dialButton {
                    id(i)
                    if (i in 1..9) {
                        num(i.toString())
                    }
                    else {
                        num(lastRow[i - 10])
                    }
                    clickListener { v ->
                        binding.callNumber.append(i.toString())
                    }
                    if (i in 2..9)
                        digits(stringList[i - 2])
                }
            }
        }
    }


    private fun setupCall() {
        /* Create transports and account. */
        val accountId = viewModel.getUserSipId()
        val username = viewModel.getUserSipId()
        val password = viewModel.getUserSipId()
        val registrar = REGISTRAR
        val proxy = PROXY
        try {
            val sipTpConfig = TransportConfig()
            sipTpConfig.port = 5061
            Global.endpoint.transportCreate(
                pjsip_transport_type_e.PJSIP_TRANSPORT_UDP,
                sipTpConfig
            )

            val accCfg = AccountConfig()
            accCfg.idUri = accountId
            accCfg.regConfig.registrarUri = registrar
            val creds = accCfg.sipConfig.authCreds
            creds.clear()
            if (!username.isNullOrEmpty()) {
                creds.add(
                    AuthCredInfo(
                        "Digest", "*", username, 0,
                        password
                    )
                )
            }
            val proxies = accCfg.sipConfig.proxies
            proxies.clear()
            if (proxy.isNotEmpty()) {
                proxies.add(proxy)
            }
//                Global.account.create(accCfg, true)
            /* Enable ICE */accCfg.natConfig.iceEnabled = true

            try {
                Global.account.create(accCfg, true)
            } catch (e: Exception) {
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        /* Destroy PJSUA2 */
        lifecycleScope.launch(Dispatchers.Default) {
            try {
                Global.endpoint.libDestroy()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}
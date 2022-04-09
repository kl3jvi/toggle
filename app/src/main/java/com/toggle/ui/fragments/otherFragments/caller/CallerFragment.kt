package com.toggle.ui.fragments.otherFragments.caller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.toggle.R
import com.toggle.databinding.CallerFragmentBinding
import com.toggle.services.Global
import com.toggle.services.MyCall
import com.toggle.services.MyLogWriter
import com.toggle.utils.PROXY
import com.toggle.utils.REGISTRAR
import com.toggle.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.pjsip.pjsua2.*

@AndroidEntryPoint
class CallerFragment : Fragment(R.layout.caller_fragment) {

    private val viewModel: CallerViewModel by viewModels()
    private val binding: CallerFragmentBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountId = viewModel.getUserSipId()
        val username = viewModel.getUserSipId()
        val password = viewModel.getUserSipId()
        val registrar = REGISTRAR
        val proxy = PROXY


        /* Setup Start button */
        binding.buttonStart.setOnClickListener {

            if (Global.endpoint.libGetState() > pjsua_state.PJSUA_STATE_NULL)
                return@setOnClickListener

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

            /* Create transports and account. */
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
                    Global.account.create(accCfg,true)
                } catch (e: Exception) {
                }
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

        /* Setup Call button */
        binding.buttonCall.setOnClickListener {
            if (Global.endpoint.libGetState() != pjsua_state.PJSUA_STATE_RUNNING)
                return@setOnClickListener

            try {
                /* Setup null audio (good for emulator) */
                Global.endpoint.audDevManager().setNullDev()

                /* Make call (to itself) */
                val call = MyCall(Global.account, -1)
                call.makeCall("sip:localhost", CallOpParam(true))
            } catch (e: Exception) {
                println(e)
            }
        }

        /* Setup Stop button */
        binding.buttonStop.setOnClickListener {
            /* Destroy PJSUA2 */
            try {
                Global.endpoint.libDestroy()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}
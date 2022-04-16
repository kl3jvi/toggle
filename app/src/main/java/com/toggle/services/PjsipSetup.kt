package com.toggle.services

import org.pjsip.pjsua2.*

/* Call implementation */
class MyCall(acc: MyAccount, call_id: Int, private val endpoint: Endpoint) : Call(acc, call_id) {


    override fun onCallState(prm: OnCallStateParam?) {
        val ci: CallInfo
        try {
            ci = info
        } catch (e: Exception) {
            println(e)
            return
        }

        endpoint.utilLogWrite(3, "MyCall", "Call state changed to: " + ci.stateText)
        if (ci.state == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
            endpoint.utilLogWrite(3, "MyCall", this.dump(true, ""))
        }
    }


    override fun onCallMediaState(prm: OnCallMediaStateParam?) {
        val ci: CallInfo
        try {
            ci = info
        } catch (e: Exception) {
            println(e)
            return
        }

        val cmiv = ci.media
        for (i in cmiv.indices) {
            val cmi = cmiv[i]
            if (cmi.type == pjmedia_type.PJMEDIA_TYPE_AUDIO &&
                (cmi.status == pjsua_call_media_status.PJSUA_CALL_MEDIA_ACTIVE ||
                        cmi.status == pjsua_call_media_status.PJSUA_CALL_MEDIA_REMOTE_HOLD)
            ) {
                /* Connect ports */
                try {
                    val am = getAudioMedia(i)
                    endpoint.audDevManager().captureDevMedia.startTransmit(am)
                    am.startTransmit(endpoint.audDevManager().playbackDevMedia)
                } catch (e: Exception) {
                    println("Failed connecting media ports" + e.message)
                    continue
                }
            }
        }
    }

}

/* Account implementation */
class MyAccount(private val endpoint: Endpoint) : Account() {

    override fun onIncomingCall(prm: OnIncomingCallParam) {
        /* Auto answer with 200 for incoming calls  */
        val call = MyCall(this, prm.callId, endpoint = endpoint)
        val ansPrm = CallOpParam()
        ansPrm.statusCode = pjsip_status_code.PJSIP_SC_OK
        try {
            call.answer(ansPrm)
        } catch (e: Exception) {
            println(e)
        }
    }
}

/* Log writer, redirects logs to stdout */
class MyLogWriter : LogWriter() {
    override fun write(entry: LogEntry) {
        println(entry.msg)
    }
}
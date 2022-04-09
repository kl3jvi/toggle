package com.toggle.utils

import java.util.*

const val BASE_URL = "https://api.toggle.com.co:3011/"
const val REGISTRAR = "sip:pbx.toggle.com.co"
const val PROXY = "sip:pbx.toggle.com.co"
const val SIP_URI = "sip:1440793901@pbx.toggle.com.co:5061"

fun randomId(): String {
    return UUID.randomUUID().toString()
}
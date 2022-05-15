package com.toggle.utils

import java.util.*

const val BASE_URL = "https://api.toggle.com.co:3011/"
const val HOST = "pbx.toggle.com.co"
const val PORT = 5060L
const val SIP_URI = "1440793901@pbx.toggle.com.co:5061"

fun randomId(): String {
    return UUID.randomUUID().toString()
}
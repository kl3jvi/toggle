package com.toggle.utils

import java.util.*

const val BASE_URL = "https://api.toggle.com.co:3011/"

fun randomId(): String {
    return UUID.randomUUID().toString()
}
package com.toggle.data.model

import java.util.*

data class SampleCall(
    val number: String,
    val provider: String,
    val date: Date = Date(System.currentTimeMillis())
)

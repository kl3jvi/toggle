package com.toggle.domain.repository

interface LocalStorage {
    var userId: String?
    var tUserId: String?
    var sipId: String?
    fun clearStorage()
}
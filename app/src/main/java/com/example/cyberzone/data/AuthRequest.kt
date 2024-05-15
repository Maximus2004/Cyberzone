package com.example.cyberzone.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val login: String,
    val password: String
)

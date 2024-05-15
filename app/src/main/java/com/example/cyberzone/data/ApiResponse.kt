package com.example.cyberzone.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean
)
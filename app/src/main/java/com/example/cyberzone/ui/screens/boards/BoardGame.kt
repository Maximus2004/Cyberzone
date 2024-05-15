package com.example.cyberzone.ui.screens.boards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BoardGame(
    val id: String = "4scd3",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val name: String = "UNO",
    val count: Long = 1,
    @SerialName("image_uri")
    val imageURI: String = "",
    @SerialName("left_count")
    val leftCount: Long = 1,
    val available: Boolean = true
)
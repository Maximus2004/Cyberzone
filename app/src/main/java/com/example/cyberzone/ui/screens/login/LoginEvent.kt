package com.example.cyberzone.ui.screens.login

sealed interface LoginEvent {
    data class OnLoginChanged(val login: String): LoginEvent
    data class OnPasswordChanged(val password: String): LoginEvent
}
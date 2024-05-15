package com.example.cyberzone.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.cyberzone.data.AuthRequest
import com.example.cyberzone.data.BoardGamesRepository
import com.example.cyberzone.ui.screens.home.CurrentLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val boardGamesRepository: BoardGamesRepository) : ViewModel() {
    private val _loginUiState: MutableStateFlow<AuthRequest> = MutableStateFlow(AuthRequest(login = "", password = ""))
    val loginUiState: StateFlow<AuthRequest> = _loginUiState.asStateFlow()

    fun onLoginEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnLoginChanged -> {
                _loginUiState.update { it.copy(login = event.login) }
            }
            is LoginEvent.OnPasswordChanged -> {
                _loginUiState.update { it.copy(password = event.password) }
            }
        }
    }
}
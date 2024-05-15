package com.example.cyberzone.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cyberzone.TAG_CONST
import com.example.cyberzone.data.BoardGamesRepository
import com.example.cyberzone.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val boardGamesRepository: BoardGamesRepository) : ViewModel() {
    private val _currentLoadingState: MutableStateFlow<CurrentLoadingState> = MutableStateFlow(CurrentLoadingState.Loading)
    val currentLoadingState: StateFlow<CurrentLoadingState> = _currentLoadingState.asStateFlow()

    private val _userAuthState: MutableStateFlow<AnyProgressState?> = MutableStateFlow(null)
    val userAuthState: StateFlow<AnyProgressState?> = _userAuthState.asStateFlow()

    fun onHomeEvent(event: HomeEvent) = viewModelScope.launch {
        when (event) {
            is HomeEvent.OnScanBoardGame -> {
                try {
                    Log.i(TAG_CONST, event.boardGameData.id)
                    _currentLoadingState.update { CurrentLoadingState.Success(HomeUiState()) }
                } catch (e: HttpException) {
                    _currentLoadingState.update { CurrentLoadingState.Fail }
                    Log.e(TAG_CONST, e.message())
                }
            }

            is HomeEvent.OnTakeBoardGame -> {
                try {
                    boardGamesRepository.takeBoardGame(event.boardGameData)
                } catch (e: HttpException) {
                    Log.e(TAG_CONST, e.message())
                }
            }

            is HomeEvent.OnLoginUser -> {
                try {
                    val response = boardGamesRepository.auth(event.authRequest)
                    if (response.isSuccessful) {
                        Log.i(TAG_CONST, response.code().toString())
                        Log.i(TAG_CONST, response.message())
                        Log.i(TAG_CONST, response.body().toString())
                        Log.i(TAG_CONST, response.errorBody().toString())
                        Log.i(TAG_CONST, response.raw().toString())

                        _userAuthState.update { AnyProgressState.Success(User(accessToken = "", refreshToken = "", tokenType = "")) }
                    } else {
                        Log.i(TAG_CONST, response.code().toString())
                        _userAuthState.update { AnyProgressState.Fail }
                    }
                } catch (e: HttpException) {
                    _userAuthState.update { AnyProgressState.Fail }
                    Log.e(TAG_CONST, e.message())
                }
            }
        }
    }
}

sealed interface CurrentLoadingState {
    data class Success(val homeUiState: HomeUiState) : CurrentLoadingState
    data object Loading : CurrentLoadingState
    data object Fail : CurrentLoadingState
}

sealed interface AnyProgressState {
    data class Success(val user: User) : AnyProgressState
    data object Fail : AnyProgressState
}

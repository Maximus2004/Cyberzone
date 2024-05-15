package com.example.cyberzone.ui.screens.home

import com.example.cyberzone.data.AuthRequest
import com.example.cyberzone.data.BoardGameData
import com.example.cyberzone.data.User

sealed interface HomeEvent {
    data class OnScanBoardGame(val boardGameData: BoardGameData): HomeEvent
    data class OnTakeBoardGame(val boardGameData: BoardGameData): HomeEvent
    data class OnLoginUser(val authRequest: AuthRequest): HomeEvent
}
package com.example.cyberzone.data

import android.util.Log
import com.example.cyberzone.TAG_CONST
import com.example.cyberzone.network.BoardGamesApiService
import com.example.cyberzone.ui.screens.boards.BoardGame
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BoardGamesRepository @Inject constructor(private val boardGamesApiService: BoardGamesApiService) {
    suspend fun takeBoardGame(boardGameData: BoardGameData): ApiResponse {
        return boardGamesApiService.takeBoardGame(boardGameData)
    }
    suspend fun getBoardGameById(gameId: String, authToken: String): BoardGame {
        Log.i(TAG_CONST, "Bearer $authToken")
        return boardGamesApiService.getBoardGameById(id = gameId, authToken = "Bearer $authToken")
    }
    suspend fun auth(authRequest: AuthRequest): Response<String> {
        return boardGamesApiService.auth(authRequest)
    }
}
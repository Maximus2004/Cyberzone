package com.example.cyberzone.network

import com.example.cyberzone.data.ApiResponse
import com.example.cyberzone.data.AuthRequest
import com.example.cyberzone.data.BoardGameData
import com.example.cyberzone.data.User
import com.example.cyberzone.ui.screens.boards.BoardGame
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface BoardGamesApiService {
    @POST("board_game/reserve/qr")
    suspend fun takeBoardGame(@Body boardGameData: BoardGameData): ApiResponse

    @GET("board_game/{id}")
    suspend fun getBoardGameById(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): BoardGame

    @Headers("Host: esports.mirea.ru", "Content-Length: 61", "Authorization: Bearer v4.public.eyJleHAiOiAxNzEyMzQ0NDI3LjI4MzY0NywgInN1YiI6ICJhZmU2OTQxYS1kYTZkLTExZWUtOWJjMi03YjhjNmNjYzhjYWEifZLGgVBaepzSmqFPei8SiKMl7TJxh5l8ZI-ibPR2fyZcin4_Q-JGvYEDc-9719tSLN7SGS4K_a3ESYgsYvpvBwQ")
    @POST("auth/user/login")
    suspend fun auth(@Body authRequest: AuthRequest): Response<String>
}
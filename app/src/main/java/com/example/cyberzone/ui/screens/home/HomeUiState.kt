package com.example.cyberzone.ui.screens.home

import com.example.cyberzone.data.User
import com.example.cyberzone.ui.screens.boards.BoardGame

data class HomeUiState(
    val currentBoard: BoardGame = BoardGame(),
)

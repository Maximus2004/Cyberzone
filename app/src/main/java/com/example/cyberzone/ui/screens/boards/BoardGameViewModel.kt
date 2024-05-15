package com.example.cyberzone.ui.screens.boards

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardGameViewModel {
    private var _boardGameUiState = MutableStateFlow(BoardGameUiState())
    val boardGameUiState = _boardGameUiState.asStateFlow()
}
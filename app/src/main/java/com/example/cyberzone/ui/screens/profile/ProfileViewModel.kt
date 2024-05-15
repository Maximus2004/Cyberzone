package com.example.cyberzone.ui.screens.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cyberzone.data.ApiResponse
import com.example.cyberzone.data.BoardGameData
import com.example.cyberzone.data.BoardGamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): ViewModel() {
    fun onProfileEvent(event: ProfileEvent) {

    }
}
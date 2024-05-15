package com.example.cyberzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cyberzone.ui.screens.home.HomeScreen
import com.example.cyberzone.ui.theme.CyberzoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CyberzoneTheme {
                HomeScreen()
            }
        }
    }
}
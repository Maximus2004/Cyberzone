package com.example.cyberzone.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cyberzone.ui.theme.CyberzoneTheme

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = hiltViewModel()) {
    ProfileScreenContent()
}

@Composable
fun ProfileScreenContent() {
    Column(modifier = Modifier) {
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
    CyberzoneTheme {
        ProfileScreenContent()
    }
}
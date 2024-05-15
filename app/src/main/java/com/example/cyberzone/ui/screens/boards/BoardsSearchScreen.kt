package com.example.cyberzone.ui.screens.boards

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cyberzone.ui.theme.CyberzoneTheme

object BoardsSearchScreen {
    const val route = "BoardsSearchScreen"
}

@Composable
fun BoardsSearchScreen() {
    SearchField(modifier = Modifier.padding(vertical = 25.dp, horizontal = 27.dp))
}

@Preview
@Composable
fun PreviewProfileScreen() {
    CyberzoneTheme {
        BoardsSearchScreen()
    }
}
package com.example.cyberzone.ui.screens.home

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cyberzone.TAG_CONST
import com.example.cyberzone.data.navigationItemContentList
import com.example.cyberzone.ui.BottomNavigationBar
import com.example.cyberzone.ui.TopBar
import com.example.cyberzone.ui.TopBarLogin
import com.example.cyberzone.ui.screens.boards.BoardGameScreen
import com.example.cyberzone.ui.screens.boards.BoardsSearchScreen
import com.example.cyberzone.ui.screens.login.LoginScreen
import com.example.cyberzone.ui.screens.login.LoginViaLkScreen
import com.example.cyberzone.ui.screens.login.LoginViewModel
import com.example.cyberzone.ui.screens.profile.WebViewLogin

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: String = navBackStackEntry?.destination?.route ?: BoardsSearchScreen.route

    val homeViewModel: HomeViewModel = hiltViewModel()
    val currentLoadingState by homeViewModel.currentLoadingState.collectAsState()
    val userAuthState by homeViewModel.userAuthState.collectAsState()

    var isTopBarDisplayed by remember { mutableStateOf(true) }
    var isBottomBarDisplayed by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            if (isTopBarDisplayed && (userAuthState == null || userAuthState is AnyProgressState.Fail))
                TopBarLogin(navigate = navController::navigate)
            else if (isTopBarDisplayed)
                TopBar()
        },
        bottomBar = {
            if (isBottomBarDisplayed)
                BottomNavigationBar(
                    currentTab = currentRoute,
                    onTabPressed = { pageType ->
                        navController.navigate(pageType)
                    },
                    navigationItemContentList = navigationItemContentList,
                    onHomeEvent = homeViewModel::onHomeEvent
                )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background),
            startDestination = LoginViaLkScreen.route
        ) {
            composable(route = BoardsSearchScreen.route) {
                BoardsSearchScreen()
                isBottomBarDisplayed = true
            }
            composable(route = BoardGameScreen.route) {
                BoardGameScreen(
                    currentLoadingState = currentLoadingState,
                    onHomeEvent = homeViewModel::onHomeEvent
                )
                isBottomBarDisplayed = true
            }
            composable(route = LoginScreen.route) {
                LoginScreen(navigate = navController::navigate)
                isBottomBarDisplayed = false
                isTopBarDisplayed = true
            }
            composable(route = WebViewLogin.route) {
                WebViewLogin(navigate = navController::navigate)
                isTopBarDisplayed = false
                isBottomBarDisplayed = false
            }
            composable(route = LoginViaLkScreen.route) {
                LoginViaLkScreen(navigate = navController::navigate)
                isBottomBarDisplayed = false
                isTopBarDisplayed = true
            }
        }
    }
}

package com.example.cyberzone.ui.screens.profile

import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.cyberzone.ui.screens.boards.BoardsSearchScreen
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

object WebViewLogin {
    const val route = "WebViewLogin"
}

@Composable
fun WebViewLogin(navigate: (String) -> Unit) {
    val context = LocalContext.current

    var url by remember { mutableStateOf("https://esports.mirea.ru/api/v1/login?service=mobile") }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()

    val webClient = remember {
        object : AccompanistWebViewClient() {
            override fun onPageStarted(
                view: WebView,
                url: String?,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                if (Uri.parse(url).getQueryParameter("access_token") != null) {
                    Toast.makeText(context, "Успешная авторизация", Toast.LENGTH_LONG).show()
                    navigate(BoardsSearchScreen.route)
                }
            }
        }
    }

    WebView(
        state = state,
        modifier = Modifier.fillMaxSize(),
        navigator = navigator,
        onCreated = { webView -> webView.settings.javaScriptEnabled = true },
        client = webClient
    )
}
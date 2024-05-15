package com.example.cyberzone.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cyberzone.R
import com.example.cyberzone.TAG_CONST
import com.example.cyberzone.data.BoardGameData
import com.example.cyberzone.data.navigationItemContentList
import com.example.cyberzone.ui.screens.boards.BoardGameScreen
import com.example.cyberzone.ui.screens.home.HomeEvent
import com.example.cyberzone.ui.screens.home.NavigationItemContent
import com.example.cyberzone.ui.screens.login.LoginScreen
import com.example.cyberzone.ui.screens.profile.WebViewLogin
import com.example.cyberzone.ui.theme.CyberzoneTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch

@Composable
fun TopBarLogin(navigate: (String) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 71.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null,
            modifier = Modifier.padding(start = 29.dp)
        )
        Spacer(Modifier.weight(1f))
        Button(
            onClick = { navigate(WebViewLogin.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .heightIn(max = 32.dp)
                .widthIn(min = 90.dp)
                .padding(end = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.scrim
            )
        }
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 10.dp)) {
            Icon(
                imageVector = Icons.Default.Menu,
                modifier = Modifier.size(height = 32.dp, width = 32.dp),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = null
            )
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 71.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null,
            modifier = Modifier.padding(start = 29.dp)
        )
        Spacer(Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.notifications_exist),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 10.dp)) {
            Icon(
                imageVector = Icons.Default.Menu,
                modifier = Modifier.size(height = 32.dp, width = 32.dp),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = null
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    onHomeEvent: (HomeEvent) -> Unit,
    currentTab: String,
    onTabPressed: ((String) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val scanLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        coroutineScope.launch {
            if (result.contents != null) {
                onHomeEvent(HomeEvent.OnScanBoardGame(BoardGameData(result.contents)))
                onTabPressed(BoardGameScreen.route)
                Toast.makeText(context, context.getString(R.string.qr_scan_success), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString(R.string.qr_scan_fail), Toast.LENGTH_SHORT).show()
            }
        }
    }

    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 79.dp),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.pageType,
                onClick = {
                    if (navItem.pageType == BoardGameScreen.route)
                        scanLauncher.launch(ScanOptions().setPrompt("").setBeepEnabled(false))
                    else
                        onTabPressed(navItem.pageType)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = null,
                        tint = if (currentTab == navItem.pageType) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = navItem.pageText),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTopBarLogin() {
    CyberzoneTheme {
        TopBarLogin(navigate = {})
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTopBar() {
    CyberzoneTheme {
        TopBar()
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewBottomBar() {
    CyberzoneTheme {
        BottomNavigationBar(
            currentTab = "HomeScreen",
            onTabPressed = {},
            navigationItemContentList = navigationItemContentList,
            onHomeEvent = {}
        )
    }
}
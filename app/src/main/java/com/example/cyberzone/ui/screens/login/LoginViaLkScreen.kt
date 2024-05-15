package com.example.cyberzone.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cyberzone.R
import com.example.cyberzone.ui.screens.profile.WebViewLogin
import com.example.cyberzone.ui.theme.CyberzoneTheme

object LoginViaLkScreen {
    const val route = "LoginViaLkScreen"
}

@Composable
fun LoginViaLkScreen(navigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(top = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.entrance),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.displaySmall
        )
        OutlinedButton(
            onClick = { navigate(WebViewLogin.route) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 45.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.gerb),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = stringResource(R.string.login_via_lk),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(start = 14.dp)
                )
            }
        }
        TextButton(onClick = { navigate(LoginScreen.route) }) {
            Text(
                text = stringResource(R.string.enter_for_admin),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.secondaryContainer,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginViaLkScreen() {
    CyberzoneTheme {
        LoginViaLkScreen(navigate = {})
    }
}
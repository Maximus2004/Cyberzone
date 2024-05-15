package com.example.cyberzone.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cyberzone.R
import com.example.cyberzone.data.AuthRequest
import com.example.cyberzone.data.User
import com.example.cyberzone.ui.screens.boards.BoardsSearchScreen
import com.example.cyberzone.ui.screens.home.AnyProgressState
import com.example.cyberzone.ui.screens.home.HomeEvent
import com.example.cyberzone.ui.theme.CyberzoneTheme

object LoginScreen {
    const val route = "LoginScreen"
}

@Composable
fun LoginScreen(navigate: (String) -> Unit, ) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val loginUiState by loginViewModel.loginUiState.collectAsState()

    LoginScreenContent(
        navigate = navigate,
        loginUiState = loginUiState,
        onLoginEvent = loginViewModel::onLoginEvent
    )
}

@Composable
fun LoginScreenContent(
    navigate: (String) -> Unit,
    loginUiState: AuthRequest,
    onLoginEvent: (LoginEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(top = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = stringResource(R.string.entrance),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.displaySmall
            )
            Column(modifier = Modifier.padding(top = 40.dp, bottom = 30.dp)) {
                Text(
                    text = stringResource(R.string.email),
                    style = MaterialTheme.typography.bodySmall
                )
                LoginTextField(
                    value = loginUiState.login,
                    onValueChanged = { onLoginEvent(LoginEvent.OnLoginChanged(it)) },
                    modifier = Modifier.padding(top = 8.dp),
                    label = stringResource(R.string.your_email)
                )
            }
            Column {
                Text(
                    text = stringResource(R.string.password),
                    style = MaterialTheme.typography.bodySmall
                )
                LoginTextField(
                    value = loginUiState.password,
                    onValueChanged = { onLoginEvent(LoginEvent.OnPasswordChanged(it)) },
                    modifier = Modifier.padding(top = 8.dp),
                    label = stringResource(R.string.type_text_here),
                    isPassword = true
                )
            }
            Button(
                onClick = { navigate(BoardsSearchScreen.route) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 20.dp)
                    .heightIn(min = 60.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}

@Composable
fun LoginTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 50.dp)
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 2.dp,
                brush = SolidColor(MaterialTheme.colorScheme.secondaryContainer)
            ),
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                )
            }
        },
        singleLine = true
    )
}

@Preview
@Composable
fun PreviewLoginScreen() {
    CyberzoneTheme {
        LoginScreenContent(
            onLoginEvent = {},
            loginUiState = AuthRequest(login = "", password = ""),
            navigate = {}
        )
    }
}
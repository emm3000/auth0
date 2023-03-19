package com.emm.auth0app.presentation.screens.login

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.emm.auth0app.presentation.composeutils.UseEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = koinViewModel()
) {

    val state = vm.loginState.collectAsState()

    LoginScreen(
        loginState = state.value,
        onLoginClick = vm::onLoginClick,
        onSuccessLogin = { navController.navigate("home") }
    )
}

@Composable
private fun LoginScreen(
    loginState: LoginState,
    onLoginClick: (Context) -> Unit,
    onSuccessLogin: () -> Unit
) {

    val context = LocalContext.current

    UseEffect(loginState) {
        if (loginState.isSuccess) {
            onSuccessLogin.invoke()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onLoginClick(context) }) {
            Text(text = "LOGIN")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "LOGOUT")
        }
        if (loginState.isSuccess) {
            CircularProgressIndicator()
        }
    }
}
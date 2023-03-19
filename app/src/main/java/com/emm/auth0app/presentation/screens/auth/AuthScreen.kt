package com.emm.auth0app.presentation.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.emm.auth0app.presentation.composeutils.UseEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    navController: NavController,
    vm: AuthViewModel = koinViewModel()
) {

    val state = vm.authState.collectAsState()

    AuthScreen(
        authState = state.value,
        toLoginNavigate = {
            navController.navigate("login") {
                popUpTo("auth") {
                    inclusive = true
                }
            }
        },
        toHomeNavigate = {
            navController.navigate("home") {
                popUpTo("auth") {
                    inclusive = true
                }
            }
        }
    )
}

@Composable
private fun AuthScreen(
    authState: AuthState,
    toLoginNavigate: () -> Unit,
    toHomeNavigate: () -> Unit,
) {

    UseEffect(authState) {
        when (authState) {
            AuthState.ExistedSession -> toHomeNavigate.invoke()
            AuthState.NoSession -> toLoginNavigate.invoke()
            AuthState.None -> {}
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        authState = AuthState.None,
        {}, {}
    )
}
package com.emm.auth0app.presentation.screens.firebase.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emm.auth0app.presentation.composeutils.UseEffect
import com.emm.auth0app.presentation.screens.firebase.login.state.LoginFirebaseState
import org.koin.androidx.compose.koinViewModel

@Composable
fun FirebaseLoginScreen(
    navController: NavController,
    vm: LoginFirebaseViewModel = koinViewModel()
) {

    val state = vm.state.collectAsState()

    FirebaseLoginScreen(
        toRegister = { navController.navigate("register") },
        toHome = {
            navController.navigate("firebase") {
                popUpTo("login") {
                    inclusive = true
                }
            }
        },
        loginFirebaseState = state.value,
        loginAction = vm::login
    )
}

@Composable
private fun FirebaseLoginScreen(
    loginFirebaseState: LoginFirebaseState,
    toRegister: () -> Unit,
    toHome: () -> Unit,
    loginAction: (String, String) -> Unit
) {

    val context = LocalContext.current

    val (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }

    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }

    UseEffect(loginFirebaseState) {
        if (loginFirebaseState is LoginFirebaseState.Success) {
            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
            toHome.invoke()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

        )
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(onClick = {
                loginAction.invoke(email, password)
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Login")
            }
            OutlinedButton(onClick = toRegister, modifier = Modifier.weight(1f)) {
                Text(text = "Register")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        if (loginFirebaseState is LoginFirebaseState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirebaseLoginPreview() {
    FirebaseLoginScreen(
        toRegister = {},
        loginFirebaseState = LoginFirebaseState.Initial,
        loginAction = { _, _ -> },
        toHome = {}
    )
}
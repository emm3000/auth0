package com.emm.auth0app.presentation.screens.firebase.register

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.emm.auth0app.presentation.composeutils.UseEffect
import com.emm.auth0app.presentation.composeutils.log
import com.emm.auth0app.presentation.screens.firebase.register.state.RegisterFirebaseState
import org.koin.androidx.compose.koinViewModel

@Composable
fun FirebaseRegisterScreen(
    navController: NavHostController,
    vm: RegisterFirebaseViewModel = koinViewModel()
) {

    val state = vm.state.collectAsState()

    FirebaseRegisterScreen(
        firebaseRegisterState = state.value,
        onRegister = vm::register,
        onSuccessRegister = {
            navController.popBackStack()
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun FirebaseRegisterScreen(
    firebaseRegisterState: RegisterFirebaseState,
    onRegister: (String, String) -> Unit,
    onSuccessRegister: () -> Unit
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }

    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }

    UseEffect(firebaseRegisterState) {
        if (firebaseRegisterState is RegisterFirebaseState.Success) {
            Toast.makeText(context, "Successfully Register", Toast.LENGTH_SHORT).show()
            onSuccessRegister.invoke()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Insert Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Insert Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(onClick = {
                keyboardController?.hide()
                onRegister.invoke(email, password)
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Register")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Box(
            modifier = Modifier.size(50.dp)
                .align(CenterHorizontally)
        ) {
            if (firebaseRegisterState is RegisterFirebaseState.Loading) {
                CircularProgressIndicator()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FirebaseRegisterScreenPreview() {
    FirebaseRegisterScreen(
        firebaseRegisterState = RegisterFirebaseState.Initial,
        onRegister = { _, _ -> },
        onSuccessRegister = {}
    )
}
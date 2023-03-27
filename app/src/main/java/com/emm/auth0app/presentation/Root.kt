package com.emm.auth0app.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emm.auth0app.presentation.screens.auth.AuthScreen
import com.emm.auth0app.presentation.screens.chat.ContactsScreen
import com.emm.auth0app.presentation.screens.firebase.login.FirebaseLoginScreen
import com.emm.auth0app.presentation.screens.firebase.register.FirebaseRegisterScreen
import com.emm.auth0app.presentation.screens.firebase.FirebaseScreen
import com.emm.auth0app.presentation.screens.home.HomeScreen
import com.emm.auth0app.presentation.screens.login.LoginScreen

@Composable
fun Root() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "firebase") {
        composable("auth") { AuthScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("choa") { ContactsScreen() }
    }

}

@Composable
fun ChatApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { FirebaseLoginScreen(navController) }
        composable("firebase") { FirebaseScreen(navController) }
        composable("register") { FirebaseRegisterScreen(navController) }
    }
}
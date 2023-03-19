package com.emm.auth0app.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.emm.auth0app.presentation.screens.home.HomeScreen
import com.emm.auth0app.presentation.screens.login.LoginScreen

@Composable
fun Root() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
    }

}
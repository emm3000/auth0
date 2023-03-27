package com.emm.auth0app.presentation.screens.firebase

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun FirebaseScreen(
    navController: NavController,
    vm: FirebaseViewModel = koinViewModel()
) {
    Text(text = "Como es la nuez")
}
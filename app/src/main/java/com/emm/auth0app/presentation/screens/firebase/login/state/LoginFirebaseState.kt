package com.emm.auth0app.presentation.screens.firebase.login.state

sealed interface LoginFirebaseState {
    object Loading : LoginFirebaseState
    object Success : LoginFirebaseState
    object Initial : LoginFirebaseState
}
package com.emm.auth0app.presentation.screens.firebase.register.state

sealed class RegisterFirebaseState {

    object Loading: RegisterFirebaseState()
    object Success: RegisterFirebaseState()
    object Initial: RegisterFirebaseState()
}
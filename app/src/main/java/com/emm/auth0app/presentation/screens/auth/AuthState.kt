package com.emm.auth0app.presentation.screens.auth

sealed interface AuthState {

    object None: AuthState
    object ExistedSession: AuthState
    object NoSession: AuthState
}
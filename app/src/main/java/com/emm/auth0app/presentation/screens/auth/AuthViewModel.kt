package com.emm.auth0app.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.auth0app.data.ds.AuthManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authManager: AuthManager
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.None)
    val authState get() = _authState.asStateFlow()

    init {
        validateIfExistSession()
    }

    private fun validateIfExistSession() = viewModelScope.launch {
        delay(2000L)
        if (authManager.isAuthenticated()) {
            _authState.value = AuthState.ExistedSession
        } else {
            _authState.value = AuthState.NoSession
        }
    }

}
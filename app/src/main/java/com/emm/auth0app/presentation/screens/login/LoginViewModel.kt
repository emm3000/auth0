package com.emm.auth0app.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.auth0app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun onLoginClick() {
        _loginState.value = _loginState.value.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            val isSuccess = authRepository.login() != null
            _loginState.value = _loginState.value.copy(
                isLoading = false,
                isSuccess = isSuccess,
                isError = !isSuccess
            )
        }
    }

}
package com.emm.auth0app.presentation.screens.firebase.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.auth0app.domain.repository.FirebaseAuthRepository
import com.emm.auth0app.presentation.screens.firebase.login.state.LoginFirebaseState
import kotlinx.coroutines.flow.*

class LoginFirebaseViewModel(
    private val firebaseAuthRepository: FirebaseAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<LoginFirebaseState>(LoginFirebaseState.Initial)
    val state = _state.asStateFlow()

    fun login(email: String, password: String) {
        firebaseAuthRepository.login(email, password)
            .onStart { _state.value = LoginFirebaseState.Loading }
            .onEach {
                Log.e("aea", it.toString())
                if (it) {
                    _state.value = LoginFirebaseState.Success
                }
            }
            .launchIn(viewModelScope)
    }

}
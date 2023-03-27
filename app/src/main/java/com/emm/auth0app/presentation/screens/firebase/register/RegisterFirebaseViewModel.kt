package com.emm.auth0app.presentation.screens.firebase.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.auth0app.data.ds.firestore.FirebaseAuthManager
import com.emm.auth0app.presentation.screens.firebase.register.state.RegisterFirebaseState
import kotlinx.coroutines.flow.*

class RegisterFirebaseViewModel(
    private val firebaseAuthManager: FirebaseAuthManager
) : ViewModel() {

    private val _state = MutableStateFlow<RegisterFirebaseState>(RegisterFirebaseState.Initial)
    val state = _state.asStateFlow()

    fun register(email: String, password: String) {
        firebaseAuthManager.register(email, password)
            .onStart { _state.value = RegisterFirebaseState.Loading }
            .onEach {
                Log.e("aea", it.toString())
                if (it) {
                    _state.value = RegisterFirebaseState.Success
                }
            }
            .launchIn(viewModelScope)
    }

}
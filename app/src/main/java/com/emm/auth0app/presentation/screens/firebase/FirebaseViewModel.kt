package com.emm.auth0app.presentation.screens.firebase

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emm.auth0app.domain.repository.FirebaseUserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID

/*
    ID de usuario (generado automáticamente por Firebase)
    Nombre de usuario
    Correo electrónico del usuario
    Foto de perfil del usuario
    Lista de chats (ID de los chats en los que participa el usuario)
 */
class FirebaseViewModel(
    private val repository: FirebaseUserRepository
) : ViewModel() {


    init {
        repository.insertUser(
            mapOf(
                "user_id" to "${Firebase.auth.currentUser?.uid}",
                "name" to "Veronica Ayde",
                "email" to "veronica@gmail.com",
                "picture" to "picture.url",
            )
        )
            .onEach {
                Log.e("aea", it.toString())
            }
            .launchIn(viewModelScope)
    }
}
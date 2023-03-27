package com.emm.auth0app.domain.repository

import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    fun login(email: String, password: String): Flow<Boolean>
    fun register(email: String, password: String): Flow<Boolean>
    fun logout()
}
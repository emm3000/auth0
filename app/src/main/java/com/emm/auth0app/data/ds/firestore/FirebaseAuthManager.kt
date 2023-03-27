package com.emm.auth0app.data.ds.firestore

import kotlinx.coroutines.flow.Flow

interface FirebaseAuthManager {

    fun login(email: String, password: String): Flow<Boolean>
    fun logout()
    fun register(email: String, password: String): Flow<Boolean>
}
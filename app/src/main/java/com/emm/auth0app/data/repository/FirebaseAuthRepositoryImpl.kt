package com.emm.auth0app.data.repository

import com.emm.auth0app.data.ds.firestore.FirebaseAuthManager
import com.emm.auth0app.data.ds.sharedpreferences.SharedPreferencesManager
import com.emm.auth0app.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf

@OptIn(FlowPreview::class)
class FirebaseAuthRepositoryImpl(
    private val firebaseAuthManager: FirebaseAuthManager,
    private val sharedPreferencesManager: SharedPreferencesManager
) : FirebaseAuthRepository {

    override fun login(email: String, password: String): Flow<Boolean> {
        return firebaseAuthManager.login(email, password).flatMapConcat {
            if (it) {
                sharedPreferencesManager.insertString(id = Firebase.auth.uid ?: "")
                return@flatMapConcat flowOf(true)
            } else return@flatMapConcat flowOf(false)
        }
    }

    override fun register(email: String, password: String): Flow<Boolean> {
        return firebaseAuthManager.register(email, password)
    }

    override fun logout() {
        return firebaseAuthManager.logout()
    }
}
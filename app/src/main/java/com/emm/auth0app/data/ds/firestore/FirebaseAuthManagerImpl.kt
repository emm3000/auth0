package com.emm.auth0app.data.ds.firestore

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class FirebaseAuthManagerImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthManager {

    override fun login(email: String, password: String): Flow<Boolean> = flow {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(true)
    }
        .catch { emit(false) }
        .flowOn(Dispatchers.IO)

    override fun logout() = firebaseAuth.signOut()

    override fun register(email: String, password: String): Flow<Boolean> = flow {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(true)
    }
        .catch { emit(false) }
        .flowOn(Dispatchers.IO)


}
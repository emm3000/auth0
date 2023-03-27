package com.emm.auth0app.data.ds.firestore

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class FirebaseUserImpl(
    private val db: FirebaseFirestore
) : FirebaseUser {

    override fun getUsers() {
    }

    override fun insertUser(user: Map<String, Any>): Flow<Boolean> = flow {
        db.collection("users")
            .document(user["user_id"].toString())
            .set(user)
            .await()
        emit(true)
    }.catch {
        emit(false)
    }.flowOn(Dispatchers.IO)

    override fun deleteUser() {
        TODO("Not yet implemented")
    }

    override fun updateUser() {
        TODO("Not yet implemented")
    }

    override fun getUserById() {
        TODO("Not yet implemented")
    }
}
package com.emm.auth0app.data.repository

import com.emm.auth0app.data.ds.firestore.FirebaseUser
import com.emm.auth0app.domain.repository.FirebaseUserRepository
import kotlinx.coroutines.flow.Flow

class FirebaseUserRepositoryImpl(
    private val firebaseUser: FirebaseUser
) : FirebaseUserRepository {

    override fun insertUser(user: Map<String, Any>): Flow<Boolean> {
        return firebaseUser.insertUser(user)
    }
}
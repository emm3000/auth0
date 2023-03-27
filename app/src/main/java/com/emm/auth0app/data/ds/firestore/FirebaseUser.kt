package com.emm.auth0app.data.ds.firestore

import kotlinx.coroutines.flow.Flow

interface FirebaseUser {

    fun getUsers()
    fun insertUser(user: Map<String, Any>): Flow<Boolean>
    fun deleteUser()
    fun updateUser()
    fun getUserById()
}
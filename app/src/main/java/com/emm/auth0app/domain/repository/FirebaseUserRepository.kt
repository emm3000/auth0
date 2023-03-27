package com.emm.auth0app.domain.repository

import kotlinx.coroutines.flow.Flow

interface FirebaseUserRepository {

    fun insertUser(user: Map<String, Any>): Flow<Boolean>
}
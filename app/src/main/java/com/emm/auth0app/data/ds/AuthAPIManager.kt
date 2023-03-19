package com.emm.auth0app.data.ds

import com.auth0.android.result.UserProfile

interface AuthAPIManager {

    suspend fun getUserInfo(token: String): UserProfile?
    suspend fun getUserMetadata(token: String, id: String): UserProfile?
    suspend fun updateUserMetadata(
        token: String,
        id: String,
        metadata: Map<String, String>
    ): UserProfile?
}
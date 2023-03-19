package com.emm.auth0app.data.ds

import com.auth0.android.result.UserProfile

interface AuthAPIManager {

    suspend fun getUserInfo(token: String): UserProfile?
    suspend fun getUserMetadata(token: String)
    suspend fun updateUserMetadata(token: String)
}
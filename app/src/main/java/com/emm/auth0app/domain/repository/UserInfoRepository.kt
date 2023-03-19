package com.emm.auth0app.domain.repository

import com.auth0.android.result.UserProfile

interface UserInfoRepository {

    suspend fun getUserInfo(): UserProfile?
    suspend fun getUserMetadataInfo(): String
    suspend fun updateUserMetadataInfo(country: String): String
    suspend fun validateIfCanConsumeAPI(): Boolean
}
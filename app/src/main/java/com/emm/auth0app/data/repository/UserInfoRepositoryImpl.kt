package com.emm.auth0app.data.repository

import com.auth0.android.jwt.JWT
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.emm.auth0app.data.ds.AuthAPIManager
import com.emm.auth0app.data.ds.AuthManager
import com.emm.auth0app.domain.repository.UserInfoRepository

class UserInfoRepositoryImpl(
    private val authAPIManager: AuthAPIManager,
    private val authManager: AuthManager
) : UserInfoRepository {

    override suspend fun getUserInfo(): UserProfile? {
        val credentials: Credentials? = authManager.getCredentials()
        return credentials?.accessToken?.let { accessToken ->
            authAPIManager.getUserInfo(accessToken)
        }
    }

    override suspend fun getUserMetadataInfo(): String {
        val credentials = authManager.getCredentials() ?: return ""
        val accessToken = credentials.accessToken
        val id = credentials.user.getId() ?: return ""
        return authAPIManager
            .getUserMetadata(accessToken, id)
            ?.getUserMetadata()?.get("country") as String? ?: ""
    }

    override suspend fun updateUserMetadataInfo(country: String): String {
        val credentials = authManager.getCredentials() ?: return ""
        val accessToken = credentials.accessToken
        val id = credentials.user.getId() ?: return ""
        return authAPIManager
            .updateUserMetadata(accessToken, id, mapOf("country" to country))
            ?.getUserMetadata()?.get("country") as String? ?: ""
    }

    override suspend fun validateIfCanConsumeAPI(): Boolean {
        val credentials = authManager.getCredentials() ?: return false
        val jwt = JWT(credentials.accessToken)
        return jwt.audience?.contains("xx") ?: false
    }

}
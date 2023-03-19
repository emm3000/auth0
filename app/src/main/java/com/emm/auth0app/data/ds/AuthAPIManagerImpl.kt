package com.emm.auth0app.data.ds

import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthAPIManagerImpl(
    private val account: Auth0
): AuthAPIManager {

    override suspend fun getUserInfo(token: String) = withContext(Dispatchers.IO) {
        return@withContext try {
            val client = AuthenticationAPIClient(account)
            client.userInfo(token).await()
        } catch (e: AuthenticationException) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getUserMetadata(token: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserMetadata(token: String) {
        TODO("Not yet implemented")
    }
}
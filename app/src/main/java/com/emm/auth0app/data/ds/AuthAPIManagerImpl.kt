package com.emm.auth0app.data.ds

import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.result.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthAPIManagerImpl(
    private val authenticationAPIClient: AuthenticationAPIClient,
    private val account: Auth0
) : AuthAPIManager {

    override suspend fun getUserInfo(token: String): UserProfile? = withContext(Dispatchers.IO) {
        try {
            authenticationAPIClient.userInfo(token).await()
        } catch (e: AuthenticationException) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getUserMetadata(token: String, id: String): UserProfile? =
        withContext(Dispatchers.IO) {
            try {
                val usersClient = UsersAPIClient(account, token)
                usersClient.getProfile(id).await()
            } catch (e: ManagementException) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun updateUserMetadata(
        token: String,
        id: String,
        metadata: Map<String, String>
    ): UserProfile? = withContext(Dispatchers.IO) {
        try {
            val usersClient = UsersAPIClient(account, token)
            usersClient.updateMetadata(id, metadata).await()
        } catch (e: ManagementException) {
            e.printStackTrace()
            null
        }

    }
}
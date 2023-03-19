package com.emm.auth0app.data.ds

import android.content.Context
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val SCOPE = "openid profile email read:current_user update:current_user_metadata"

class Auth0Manager(
    private val account: Auth0,
    private val schema: String,
    private val audience: String
): AuthManager {

    override suspend fun login(context: Context): Credentials? = withContext(Dispatchers.IO) {
        try {
            val credentials: Credentials = WebAuthProvider.login(account)
                .withScheme(schema)
                .withScope(SCOPE)
                .withAudience(audience)
                .await(context)
            credentials
        } catch (e: AuthenticationException) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun logout(context: Context) = withContext(Dispatchers.IO) {
        try {
            WebAuthProvider.logout(account)
                .withScheme(schema)
                .await(context)
        } catch (e: AuthenticationException) {
            e.printStackTrace()
        }
    }

}
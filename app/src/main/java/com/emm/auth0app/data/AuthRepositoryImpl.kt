package com.emm.auth0app.data

import android.content.Context
import com.auth0.android.result.Credentials
import com.emm.auth0app.data.ds.AuthManager
import com.emm.auth0app.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val auth0Manager: AuthManager
) : AuthRepository {

    override suspend fun login(context: Context): Credentials? {
        return auth0Manager.login(context)
    }

    override suspend fun logout(context: Context) {
        return auth0Manager.logout(context)
    }

    override fun getUserInfo() {
        TODO("Not yet implemented")
    }

    override fun pathUserInfo() {
        TODO("Not yet implemented")
    }
}
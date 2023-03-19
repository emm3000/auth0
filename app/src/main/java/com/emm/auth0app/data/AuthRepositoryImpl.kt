package com.emm.auth0app.data

import com.auth0.android.result.Credentials
import com.emm.auth0app.data.ds.AuthManager
import com.emm.auth0app.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val auth0Manager: AuthManager
) : AuthRepository {

    override suspend fun login(): Credentials? {
        return auth0Manager.login()
    }

    override suspend fun logout() {
        return auth0Manager.logout()
    }

    override fun getUserInfo() {
        TODO("Not yet implemented")
    }

    override fun pathUserInfo() {
        TODO("Not yet implemented")
    }
}
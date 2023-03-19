package com.emm.auth0app.domain.repository

import com.auth0.android.result.Credentials

interface AuthRepository {

    suspend fun login(): Credentials?

    suspend fun logout()

    fun getUserInfo()

    fun pathUserInfo()

}
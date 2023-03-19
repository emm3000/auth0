package com.emm.auth0app.data.ds

import com.auth0.android.result.Credentials

interface AuthManager {

    suspend fun login(): Credentials?
    suspend fun logout()

}
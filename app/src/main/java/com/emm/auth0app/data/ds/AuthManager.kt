package com.emm.auth0app.data.ds

import android.content.Context
import com.auth0.android.result.Credentials

interface AuthManager {

    suspend fun login(context: Context): Credentials?
    suspend fun logout(context: Context)
    suspend fun getCredentials(): Credentials?
    fun isAuthenticated(): Boolean


}
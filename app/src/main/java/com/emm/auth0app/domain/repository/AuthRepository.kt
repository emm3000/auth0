package com.emm.auth0app.domain.repository

import android.content.Context
import com.auth0.android.result.Credentials

interface AuthRepository {

    suspend fun login(context: Context): Credentials?

    suspend fun logout(context: Context)

}
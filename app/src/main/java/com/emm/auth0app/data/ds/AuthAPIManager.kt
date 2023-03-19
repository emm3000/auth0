package com.emm.auth0app.data.ds

interface AuthAPIManager {

    suspend fun getUserInfo()
    suspend fun updateUserInfo()
}
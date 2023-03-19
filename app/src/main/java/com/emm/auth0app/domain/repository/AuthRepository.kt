package com.emm.auth0app.domain.repository

interface AuthRepository {

    fun login()

    fun logout()

    fun getUserInfo()

    fun pathUserInfo()

}
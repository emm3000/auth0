package com.emm.auth0app.presentation.screens.home

sealed class HomeState {

    object Loading: HomeState()
    object Logout: HomeState()
    class Success(
        val email: String = "",
        val name: String = "",
        val nickname: String = "",
        val familyName: String = "",
        val urlImg: String = ""
    ): HomeState()
    object None: HomeState()

}
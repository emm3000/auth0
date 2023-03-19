package com.emm.auth0app.presentation.di

import com.emm.auth0app.presentation.screens.auth.AuthViewModel
import com.emm.auth0app.presentation.screens.home.HomeViewModel
import com.emm.auth0app.presentation.screens.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::AuthViewModel)
}
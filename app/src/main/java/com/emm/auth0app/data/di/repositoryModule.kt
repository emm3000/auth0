package com.emm.auth0app.data.di

import com.emm.auth0app.data.repository.AuthRepositoryImpl
import com.emm.auth0app.data.repository.UserInfoRepositoryImpl
import com.emm.auth0app.domain.repository.AuthRepository
import com.emm.auth0app.domain.repository.UserInfoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserInfoRepository> { UserInfoRepositoryImpl(get(), get()) }
}
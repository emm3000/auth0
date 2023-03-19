package com.emm.auth0app.data.di

import com.emm.auth0app.data.AuthRepositoryImpl
import com.emm.auth0app.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}
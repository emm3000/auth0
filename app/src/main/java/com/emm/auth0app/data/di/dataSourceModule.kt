package com.emm.auth0app.data.di

import com.emm.auth0app.data.ds.Auth0Manager
import com.emm.auth0app.data.ds.AuthAPIManager
import com.emm.auth0app.data.ds.AuthAPIManagerImpl
import com.emm.auth0app.data.ds.AuthManager
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<AuthManager> {
        Auth0Manager(
            account = get(),
            credentialsManager = get(),
            schema = get(qualifier = named("schema")),
            audience = get(qualifier = named("audience")),
        )
    }

    single<AuthAPIManager> {
        AuthAPIManagerImpl(get(), get())
    }
}
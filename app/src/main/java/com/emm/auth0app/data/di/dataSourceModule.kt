package com.emm.auth0app.data.di

import com.emm.auth0app.data.ds.Auth0Manager
import com.emm.auth0app.data.ds.AuthManager
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<AuthManager> {
        Auth0Manager(
            context = androidApplication(),
            account = get(),
            schema = get(qualifier = named("schema")),
            audience = get(qualifier = named("audience")),
        )
    }
}
package com.emm.auth0app.data.di

import android.content.Context
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.storage.CredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.emm.auth0app.R
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {
    single { provideAuth0(androidApplication()) }
    single { provideCredentialsManager(androidContext(), get()) }
    single { provideAuthenticationAPIClient(get()) }
    single(named("audience")) { provideAudience(androidApplication()) }
    single(named("schema")) { provideSchema(androidApplication()) }
}

fun provideAuth0(context: Context): Auth0 {
    return Auth0(
        context.getString(R.string.com_auth0_client_id),
        context.getString(R.string.com_auth0_domain)
    )
}

fun provideCredentialsManager(
    context: Context,
    authentication: AuthenticationAPIClient
): CredentialsManager {
    val storage = SharedPreferencesStorage(context)
    return CredentialsManager(authentication, storage)
}

fun provideAuthenticationAPIClient(account: Auth0): AuthenticationAPIClient {
    return AuthenticationAPIClient(account)
}

fun provideSchema(context: Context) = context.getString(R.string.com_auth0_scheme)
fun provideAudience(context: Context) =
    "https://${context.getString(R.string.com_auth0_domain)}/api/v2/"
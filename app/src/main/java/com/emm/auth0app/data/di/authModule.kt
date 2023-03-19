package com.emm.auth0app.data.di

import android.content.Context
import com.auth0.android.Auth0
import com.emm.auth0app.R
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {
    single { provideAuth0(androidApplication()) }
    single(named("schema")) { provideSchema(androidApplication()) }
    single(named("audience")) { provideAudience(androidApplication()) }
}

fun provideAuth0(
    context: Context,
): Auth0 {
    return Auth0(
        context.getString(R.string.com_auth0_client_id),
        context.getString(R.string.com_auth0_domain)
    )
}

fun provideSchema(context: Context) = context.getString(R.string.com_auth0_scheme)
fun provideAudience(context: Context) = "https://${context.getString(R.string.com_auth0_domain)}/api/v2/"
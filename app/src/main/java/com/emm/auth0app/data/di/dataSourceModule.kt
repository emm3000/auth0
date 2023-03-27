package com.emm.auth0app.data.di

import android.content.Context
import android.content.SharedPreferences
import com.emm.auth0app.data.ds.Auth0Manager
import com.emm.auth0app.data.ds.AuthAPIManager
import com.emm.auth0app.data.ds.AuthAPIManagerImpl
import com.emm.auth0app.data.ds.AuthManager
import com.emm.auth0app.data.ds.firestore.FirebaseAuthManager
import com.emm.auth0app.data.ds.firestore.FirebaseAuthManagerImpl
import com.emm.auth0app.data.ds.firestore.FirebaseUser
import com.emm.auth0app.data.ds.firestore.FirebaseUserImpl
import com.emm.auth0app.data.ds.sharedpreferences.SharedPreferencesManager
import com.emm.auth0app.data.ds.sharedpreferences.SharedPreferencesManagerImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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

    single<FirebaseFirestore> { Firebase.firestore }
    single<FirebaseUser> { FirebaseUserImpl(get()) }
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseAuthManager> { FirebaseAuthManagerImpl(get()) }
    single<SharedPreferences> { provideSharedPreferences(androidContext()) }
    single<SharedPreferencesManager> { SharedPreferencesManagerImpl(get()) }
}

fun provideSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
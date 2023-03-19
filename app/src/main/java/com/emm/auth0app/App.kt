package com.emm.auth0app

import android.app.Application
import com.emm.auth0app.data.di.authModule
import com.emm.auth0app.data.di.dataSourceModule
import com.emm.auth0app.data.di.repositoryModule
import com.emm.auth0app.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@App)
            // Load modules
            modules(
                authModule,
                dataSourceModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
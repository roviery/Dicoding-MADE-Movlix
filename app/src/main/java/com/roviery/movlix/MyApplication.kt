package com.roviery.movlix

import android.app.Application
import com.roviery.core.di.databaseModule
import com.roviery.core.di.networkModule
import com.roviery.core.di.repositoryModule
import com.roviery.movlix.di.useCaseModule
import com.roviery.movlix.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
package com.example.multiplemodule

import android.app.Application
import com.example.multiplemodule.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class App: Application() {
    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    // PUBLIC API ---
    open fun provideComponent() = appComponent

    // CONFIGURATION ---
    open fun configureDi() =
        startKoin { androidContext(this@App )
            modules(provideComponent())
        }


}
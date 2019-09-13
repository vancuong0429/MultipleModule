package com.example.local.di

import com.example.local.MulAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
val localModule = module {
    single { MulAppDatabase.buildDatabase(androidContext()) }
    factory { get<MulAppDatabase>().userDao() }
}
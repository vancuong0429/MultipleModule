package com.template.local.di

import com.template.local.MulAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { MulAppDatabase.buildDatabase(androidContext()) }
    factory { get<MulAppDatabase>().userDao() }
}
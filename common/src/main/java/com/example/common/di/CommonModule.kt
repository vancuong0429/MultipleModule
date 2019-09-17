package com.example.common.di

import com.example.common.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val createCommonModule = module {
    single { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
}
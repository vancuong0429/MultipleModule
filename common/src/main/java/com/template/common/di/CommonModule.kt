package com.template.common.di

import com.template.common.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val createCommonModule = module {
    single { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
}
package com.example.multiplemodule.di

import com.example.common.di.createCommonModule
import com.example.detail.di.createDetailModule
import com.example.domain.di.createDomainModule
import com.example.home.di.createHomeModule
import com.example.local.di.localModule
import com.example.remote.di.createRemoteModule
import com.example.repository.di.createRepositoryModule

val appComponent= listOf(createRemoteModule("https://githubfake.free.beeceptor.com/"), createCommonModule, createDomainModule, createRepositoryModule,
    createHomeModule, createDetailModule, localModule)

package com.template.multiplemodule.di

import com.template.common.di.createCommonModule
import com.template.detail.di.createDetailModule
import com.template.domain.di.createDomainModule
import com.template.home.di.createHomeModule
import com.template.local.di.localModule
import com.template.remote.di.createRemoteModule
import com.template.repository.di.createRepositoryModule

val appComponent = listOf(
    createRemoteModule("https://api.github.com/"),
    createCommonModule,
    createDomainModule,
    createRepositoryModule,
    createHomeModule,
    createDetailModule,
    localModule
)

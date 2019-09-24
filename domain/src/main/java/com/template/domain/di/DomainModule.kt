package com.template.domain.di

import com.template.domain.usecases.GetTopUsersUseCase
import com.template.domain.usecases.GetUserDetailUseCase
import org.koin.dsl.module

val createDomainModule = module {

    factory { GetTopUsersUseCase(homeRepository = get()) }

    factory { GetUserDetailUseCase(detailRepository = get()) }
}
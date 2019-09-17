package com.example.domain.di

import com.example.domain.usecases.GetTopUsersUseCase
import com.example.domain.usecases.GetUserDetailUseCase
import org.koin.dsl.module

val createDomainModule = module{

    factory{ GetTopUsersUseCase(homeRepository = get()) }

    factory{ GetUserDetailUseCase(detailRepository = get()) }
}
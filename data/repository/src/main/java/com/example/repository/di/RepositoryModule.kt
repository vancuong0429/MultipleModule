package com.example.repository.di

import com.example.domain.repositories.HomeRepository
import com.example.domain.repositories.UserDetailRepository
import com.example.repository.DetailRepositoryImpl
import com.example.repository.HomeRepositoryImpl
import org.koin.dsl.module

val createRepositoryModule = module{
    factory <UserDetailRepository>{ DetailRepositoryImpl(userDataSource = get()) }

    factory <HomeRepository>{ HomeRepositoryImpl(userDataSource = get(), userDao = get(), remoteExceptionInterceptor = get()) }
}
package com.example.repository.di

import com.example.domain.repositories.HomeRepository
import com.example.domain.repositories.UserDetailRepository
import com.example.repository.DetailRepositoryImpl
import com.example.repository.HomeRepositoryImpl
import com.example.repository.mapper.UserLocalEntityMapper
import com.example.repository.mapper.UserRemoteEntityMapper
import com.example.repository.mapper.UserResponseLocalMapper
import org.koin.dsl.module

val createRepositoryModule = module {

    factory {
        UserRemoteEntityMapper()
    }

    factory {
        UserResponseLocalMapper()
    }

    factory {
        UserLocalEntityMapper()
    }

    factory<UserDetailRepository> {
        DetailRepositoryImpl(
            userDataSource = get(),
            remoteExceptionInterceptor = get(),
            userRemoteEntityMapper = get()
        )
    }

    factory<HomeRepository> {
        HomeRepositoryImpl(
            userDataSource = get(),
            userDao = get(),
            remoteExceptionInterceptor = get(),
            userRemoteEntityMapper = get(),
            userLocalEntityMapper = get(),
            userResponseLocalMapper = get()
        )
    }
}
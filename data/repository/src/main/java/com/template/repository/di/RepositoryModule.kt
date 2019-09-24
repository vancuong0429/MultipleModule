package com.template.repository.di

import com.template.domain.repositories.HomeRepository
import com.template.domain.repositories.UserDetailRepository
import com.template.repository.DetailRepositoryImpl
import com.template.repository.HomeRepositoryImpl
import com.template.repository.mapper.UserLocalEntityMapper
import com.template.repository.mapper.UserRemoteEntityMapper
import com.template.repository.mapper.UserResponseLocalMapper
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
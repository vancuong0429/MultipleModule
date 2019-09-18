package com.example.repository

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.UserDetailRepository
import com.example.remote.UserDataSource
import com.example.remote.exception_interceptor.RemoteExceptionInterceptor
import com.example.repository.mapper.UserEntityMapper

class DetailRepositoryImpl(private val userDataSource: UserDataSource, private val remoteExceptionInterceptor: RemoteExceptionInterceptor) : UserDetailRepository {
    private val userEntityMapper = UserEntityMapper()
    override suspend fun getUserDetail(login: String): Either<Failure, UserEntity> = Either.runSuspendWithCatchError(errorInterceptors = listOf(remoteExceptionInterceptor)){
        var response = userDataSource.fetchUserDetailsAsync(login).run {
            userEntityMapper.mapResponseToEntity(this)
        }
        return@runSuspendWithCatchError Either.Success(response)
    }

}
package com.example.repository

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.UserDetailRepository
import com.example.remote.UserDataSource
import com.example.remote.exception_interceptor.RemoteExceptionInterceptor
import com.example.repository.mapper.UserRemoteEntityMapper

class DetailRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val userRemoteEntityMapper: UserRemoteEntityMapper
) : UserDetailRepository {
    override suspend fun getUserDetail(login: String): Either<Failure, UserEntity> =
        Either.runSuspendWithCatchError(errorInterceptors = listOf(remoteExceptionInterceptor)) {
            val response = userRemoteEntityMapper.map(userDataSource.fetchUserDetailsAsync(login))
            return@runSuspendWithCatchError Either.Success(response)
        }

}
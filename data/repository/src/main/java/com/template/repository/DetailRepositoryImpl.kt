package com.template.repository

import com.template.common_jvm.exception.Failure
import com.template.common_jvm.functional.Either
import com.template.domain.entities.UserEntity
import com.template.domain.repositories.UserDetailRepository
import com.template.remote.UserDataSource
import com.template.remote.exception_interceptor.RemoteExceptionInterceptor
import com.template.repository.mapper.UserRemoteEntityMapper

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
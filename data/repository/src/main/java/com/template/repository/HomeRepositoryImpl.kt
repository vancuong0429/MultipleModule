package com.template.repository

import com.template.common_jvm.exception.Failure
import com.template.common_jvm.functional.Either
import com.template.domain.entities.UserEntity
import com.template.domain.repositories.HomeRepository
import com.template.local.dao.UserDao
import com.template.remote.UserDataSource
import com.template.remote.entities.UserResponse
import com.template.remote.exception_interceptor.RemoteExceptionInterceptor
import com.template.repository.mapper.UserLocalEntityMapper
import com.template.repository.mapper.UserRemoteEntityMapper
import com.template.repository.mapper.UserResponseLocalMapper
import java.util.*

class HomeRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val userDao: UserDao,
    private val remoteExceptionInterceptor: RemoteExceptionInterceptor,
    private val userLocalEntityMapper: UserLocalEntityMapper,
    private val userRemoteEntityMapper: UserRemoteEntityMapper,
    private val userResponseLocalMapper: UserResponseLocalMapper
) : HomeRepository {

    override suspend fun getTopUsers(): Either<Failure, List<UserEntity>> =
        Either.runSuspendWithCatchError(errorInterceptors = listOf(remoteExceptionInterceptor)) {
            val dbResult = userLocalEntityMapper.mapList(userDao.getTopUsers())
            if (dbResult.isNullOrEmpty()) {
                val response = userDataSource.fetchTopUsersAsync()
                val userDBOs = userResponseLocalMapper.mapList(response.items)
                userDao.insertUses(userDBOs)
                val dbAfterInsert = userLocalEntityMapper.mapList(userDao.getTopUsers())
                return@runSuspendWithCatchError Either.Success(dbAfterInsert)
            } else {
                return@runSuspendWithCatchError Either.Success(dbResult)
            }
        }

    override suspend fun getUserDetail(
        forceRefresh: Boolean,
        login: String
    ): Either<Failure, UserEntity> = Either.runSuspendWithCatchError {
        val userResponse: UserResponse = userDataSource.fetchUserDetailsAsync("login")
        return@runSuspendWithCatchError Either.Success(userRemoteEntityMapper.map(userResponse))
    }

}
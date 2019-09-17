package com.example.repository

import com.example.common.exception.Failure
import com.example.common.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.HomeRepository
import com.example.local.dao.UserDao
import com.example.remote.UserDataSource
import com.example.remote.entities.UserResponse
import com.example.remote.exception_interceptor.RemoteExceptionInterceptor
import com.example.repository.mapper.UserEntityMapper
import java.util.*

class HomeRepositoryImpl(private val userDataSource: UserDataSource, private val userDao: UserDao, private val remoteExceptionInterceptor: RemoteExceptionInterceptor) : HomeRepository {
    private val userEntityMapper = UserEntityMapper()
    override suspend fun getTopUsers(): Either<Failure, List<UserEntity>> = Either.runSuspendWithCatchError(errorInterceptors = listOf(remoteExceptionInterceptor)) {
        val dbResult = userDao.getTopUsers().map {
            userEntityMapper.mapDBOToEntity(it)
        }
        if (dbResult.isNullOrEmpty()) {
            val response = userDataSource.fetchTopUsersAsync()
            val userDBOs = response.items.map {
                userEntityMapper.mapFromResponseToDBO(it.apply {
                    lastRefreshed = Date()
                })
            }
            userDao.insertUses(userDBOs)
            val dbAfterInsert = userDao.getTopUsers().map {
                userEntityMapper.mapDBOToEntity(it)
            }
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
        return@runSuspendWithCatchError Either.Success(userEntityMapper.mapResponseToEntity(userResponse))
    }

}
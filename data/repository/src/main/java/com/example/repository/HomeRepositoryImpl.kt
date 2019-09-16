package com.example.repository

import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.HomeRepository
import com.example.local.dao.UserDao
import com.example.remote.UserDataSource
import com.example.repository.mapper.UserEntityMapper
import java.util.*

class HomeRepositoryImpl(private val userDataSource: UserDataSource, private val userDao: UserDao) : HomeRepository {
    private val userEntityMapper = UserEntityMapper()
    override suspend fun getTopUsers(): Resource<List<UserEntity>> {
        val dbResult = userDao.getTopUsers().map {
            userEntityMapper.mapDBOToEntity(it)
        }
        if (dbResult.isNullOrEmpty()) {
            var response = userDataSource.fetchTopUsersAsync()
            return try {
                if (response.items.isEmpty()) {
                    Resource(Resource.Status.ERROR, null, null)
                } else {
                    var userDBOs = response.items.map {
                        userEntityMapper.mapFromResponseToDBO(it.apply {
                            lastRefreshed = Date()
                        })
                    }
                    userDao.insertUses(userDBOs)
                    var dbAfterInsert = userDao.getTopUsers().map {
                        userEntityMapper.mapDBOToEntity(it)
                    }
                    Resource(Resource.Status.SUCCESS, dbAfterInsert, null)
                }
            } catch (e: Exception) {
                Resource(Resource.Status.ERROR, null, e)
            }
        } else {
            return Resource(Resource.Status.SUCCESS, dbResult, null)
        }
    }

    override suspend fun getUserDetail(
        forceRefresh: Boolean,
        login: String
    ): Resource<UserEntity> {
        return Resource(Resource.Status.SUCCESS, userDataSource.fetchUserDetailsAsync("login").run {
            userEntityMapper.mapResponseToEntity(this)
        }, null)
    }

}
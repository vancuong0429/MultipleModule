package com.example.home

import android.util.Log
import com.example.local.dao.UserDao
import com.example.model.views.User
import com.example.remote.UserDataSource
import com.example.repository.Resource
import com.example.repository.HomeRepository
import java.util.*

class HomeRepositoryImpl(private val userDataSource: UserDataSource, private val userDao: UserDao) : HomeRepository{
    override suspend fun getTopUsers(): Resource<List<User>> {
        val dbResult = userDao.getTopUsers()
        if (dbResult.isNullOrEmpty()) {
            var response = userDataSource.fetchTopUsersAsync()
            return try {
                if (response.items.isEmpty()) {
                    Resource(Resource.Status.ERROR, null, null)
                } else {
                    userDao.insertUses(response.items.apply {
                        forEach{
                            it.lastRefreshed = Date()
                        }
                    })
                    Resource(Resource.Status.SUCCESS, userDao.getTopUsers(), null)
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
    ): Resource<User> {
        return Resource(Resource.Status.SUCCESS, userDataSource.fetchUserDetailsAsync("login"), null)
    }

}
package com.example.detail

import com.example.model.views.User
import com.example.remote.UserDataSource
import com.example.repository.Resource
import com.example.repository.UserDetailRepository

class DetailRepositoryImpl(private val userDataSource: UserDataSource) : UserDetailRepository{
    override suspend fun getUserDetail(login: String): Resource<User> {
        var response = userDataSource.fetchUserDetailsAsync(login)
        return try {
            if (response == null) {
                Resource(Resource.Status.ERROR, null, null)
            } else {
                Resource(Resource.Status.SUCCESS, response, null)
            }
        } catch (e: Exception) {
            Resource(Resource.Status.ERROR, null, e)
        }
    }

}
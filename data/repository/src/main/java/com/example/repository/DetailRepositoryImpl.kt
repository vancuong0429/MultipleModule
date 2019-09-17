package com.example.repository

import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.UserDetailRepository
import com.example.remote.UserDataSource
import com.example.repository.mapper.UserEntityMapper

class DetailRepositoryImpl(private val userDataSource: UserDataSource) : UserDetailRepository {
    private val userEntityMapper = UserEntityMapper()
    override suspend fun getUserDetail(login: String): Resource<UserEntity> {
        var response = userDataSource.fetchUserDetailsAsync(login).run {
            userEntityMapper.mapResponseToEntity(this)
        }
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
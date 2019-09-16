package com.example.domain.repositories

import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity


interface UserDetailRepository  {
    suspend fun getUserDetail(login: String): Resource<UserEntity>
}
package com.example.domain.repositories

import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity


interface HomeRepository  {
     suspend fun getTopUsers(): Resource<List<UserEntity>>

     suspend fun getUserDetail(forceRefresh: Boolean = false, login: String): Resource<UserEntity>
}
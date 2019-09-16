package com.example.domain.usecases

import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.UserDetailRepository

class GetUserDetailUseCase(private val detailRepository: UserDetailRepository){
    suspend fun run(login: String): Resource<UserEntity> {
        return detailRepository.getUserDetail(login)
    }
}
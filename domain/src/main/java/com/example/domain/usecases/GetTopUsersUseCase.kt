package com.example.domain.usecases

import com.example.domain.entities.Resource
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.HomeRepository

class GetTopUsersUseCase(private val homeRepository: HomeRepository){
    suspend fun run(): Resource<List<UserEntity>> {
        return homeRepository.getTopUsers()
    }

}
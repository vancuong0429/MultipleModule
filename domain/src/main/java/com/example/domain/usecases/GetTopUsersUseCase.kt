package com.example.domain.usecases

import com.example.common.exception.Failure
import com.example.common.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.HomeRepository

class GetTopUsersUseCase(private val homeRepository: HomeRepository){
    suspend fun run(): Either<Failure,  List<UserEntity>> {
        return homeRepository.getTopUsers()
    }

}
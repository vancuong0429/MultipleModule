package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.HomeRepository

class GetTopUsersUseCase(private val homeRepository: HomeRepository){
    suspend fun run(): Either<Failure,  List<UserEntity>> {
        return homeRepository.getTopUsers()
    }

}
package com.example.domain.usecases

import com.example.common.exception.Failure
import com.example.common.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.UserDetailRepository

class GetUserDetailUseCase(private val detailRepository: UserDetailRepository){
    suspend fun run(login: String): Either<Failure, UserEntity> {
        return detailRepository.getUserDetail(login)
    }
}
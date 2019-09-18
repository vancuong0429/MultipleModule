package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.UserDetailRepository
import com.example.domain.usecases.base.UseCase
import com.example.domain.usecases.base.UseCaseParams

class GetUserDetailUseCase(private val detailRepository: UserDetailRepository) :
    UseCase<GetUserDetailUseCaseParams, UserEntity>() {

    override suspend fun executeInternal(params: GetUserDetailUseCaseParams): Either<Failure, UserEntity> {
        return detailRepository.getUserDetail(params.login)
    }
}

data class GetUserDetailUseCaseParams(val login: String) : UseCaseParams
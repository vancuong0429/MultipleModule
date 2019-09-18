package com.example.domain.usecases

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.UserEntity
import com.example.domain.repositories.HomeRepository
import com.example.domain.usecases.base.UseCase
import com.example.domain.usecases.base.UseCaseParams

class GetTopUsersUseCase(private val homeRepository: HomeRepository) :
    UseCase<UseCaseParams.Empty, List<UserEntity>>() {

    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, List<UserEntity>> {
        return homeRepository.getTopUsers()
    }
}
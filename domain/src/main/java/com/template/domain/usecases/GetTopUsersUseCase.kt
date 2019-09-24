package com.template.domain.usecases

import com.template.common_jvm.exception.Failure
import com.template.common_jvm.functional.Either
import com.template.domain.entities.UserEntity
import com.template.domain.repositories.HomeRepository
import com.template.domain.usecases.base.UseCase
import com.template.domain.usecases.base.UseCaseParams

class GetTopUsersUseCase(private val homeRepository: HomeRepository) :
    UseCase<UseCaseParams.Empty, List<UserEntity>>() {

    override suspend fun executeInternal(params: UseCaseParams.Empty): Either<Failure, List<UserEntity>> {
        return homeRepository.getTopUsers()
    }
}
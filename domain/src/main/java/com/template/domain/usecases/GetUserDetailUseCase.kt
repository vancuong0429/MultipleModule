package com.template.domain.usecases

import com.template.common_jvm.exception.Failure
import com.template.common_jvm.functional.Either
import com.template.domain.entities.UserEntity
import com.template.domain.repositories.UserDetailRepository
import com.template.domain.usecases.base.UseCase
import com.template.domain.usecases.base.UseCaseParams

class GetUserDetailUseCase(private val detailRepository: UserDetailRepository) :
    UseCase<GetUserDetailUseCaseParams, UserEntity>() {

    override suspend fun executeInternal(params: GetUserDetailUseCaseParams): Either<Failure, UserEntity> {
        return detailRepository.getUserDetail(params.login)
    }
}

data class GetUserDetailUseCaseParams(val login: String) : UseCaseParams
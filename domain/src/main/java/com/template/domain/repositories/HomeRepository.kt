package com.template.domain.repositories

import com.template.common_jvm.exception.Failure
import com.template.common_jvm.functional.Either
import com.template.domain.entities.UserEntity


interface HomeRepository {
    suspend fun getTopUsers(): Either<Failure, List<UserEntity>>

    suspend fun getUserDetail(
        forceRefresh: Boolean = false,
        login: String
    ): Either<Failure, UserEntity>
}
package com.template.domain.repositories

import com.template.common_jvm.exception.Failure
import com.template.common_jvm.functional.Either
import com.template.domain.entities.UserEntity


interface UserDetailRepository  {
    suspend fun getUserDetail(login: String): Either<Failure, UserEntity>
}
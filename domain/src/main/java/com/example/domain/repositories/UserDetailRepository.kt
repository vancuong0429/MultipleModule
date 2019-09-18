package com.example.domain.repositories

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import com.example.domain.entities.UserEntity


interface UserDetailRepository  {
    suspend fun getUserDetail(login: String): Either<Failure, UserEntity>
}
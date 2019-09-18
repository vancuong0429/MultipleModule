package com.example.domain.repositories

import com.example.common.exception.Failure
import com.example.common.functional.Either
import com.example.domain.entities.UserEntity


interface UserDetailRepository  {
    suspend fun getUserDetail(login: String): Either<Failure, UserEntity>
}
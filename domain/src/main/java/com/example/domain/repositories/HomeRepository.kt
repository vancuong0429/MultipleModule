package com.example.domain.repositories

import com.example.common.exception.Failure
import com.example.common.functional.Either
import com.example.domain.entities.UserEntity


interface HomeRepository  {
     suspend fun getTopUsers(): Either<Failure, List<UserEntity>>

     suspend fun getUserDetail(forceRefresh: Boolean = false, login: String): Either<Failure, UserEntity>
}
package com.example.domain.usecases.base

import com.example.common_jvm.exception.Failure
import com.example.common_jvm.functional.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<Params: UseCaseParams, Result>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun execute(params: Params): Either<Failure, Result> {
        return withContext(dispatcher) {
            executeInternal(params)
        }
    }

    protected abstract suspend fun executeInternal(params: Params): Either<Failure, Result>
}
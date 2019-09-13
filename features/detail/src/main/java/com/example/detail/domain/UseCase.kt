package com.example.detail.domain

import com.example.repository.Resource

abstract class UseCase<out Type> where Type : Any {
    abstract suspend fun run(login: String): Resource<Type>
}
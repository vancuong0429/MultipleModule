package com.example.home.domain

import com.example.home.CallBackI
import com.example.repository.Resource

abstract class UseCase<out Type> where Type : Any {

    abstract suspend fun run(): Resource<Type>
    abstract fun runCallback(callBackI: CallBackI)
}
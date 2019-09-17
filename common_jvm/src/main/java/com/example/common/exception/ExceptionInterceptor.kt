package com.example.common.exception

interface ExceptionInterceptor {
    fun handleException(exception: Exception) : Failure?
}
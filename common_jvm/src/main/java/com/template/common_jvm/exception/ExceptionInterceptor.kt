package com.template.common_jvm.exception

interface ExceptionInterceptor {
    fun handleException(exception: Exception) : Failure?
}
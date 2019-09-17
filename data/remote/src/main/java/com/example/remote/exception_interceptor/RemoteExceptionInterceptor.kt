package com.example.remote.exception_interceptor

import com.example.common.exception.ExceptionInterceptor
import com.example.common.exception.Failure
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RemoteExceptionInterceptor : ExceptionInterceptor {
    override fun handleException(exception: Exception): Failure? {
        return when (exception) {
            is UnknownHostException -> Failure.InternetError
            is SocketTimeoutException -> Failure.ConnectionTimeout
            is HttpException -> {
                val errorMsg = if (exception.message() != null) {
                    exception.message()
                } else {
                    ""
                }
                Failure.NetworkConnection(exception.code(), errorMsg)
            }
            else -> null
        }
    }
}
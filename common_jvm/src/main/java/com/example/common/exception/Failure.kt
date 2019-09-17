package com.example.common.exception

sealed class Failure {
    object InternetError : Failure()
    object ConnectionTimeout : Failure()
    class ApiError(val httpCode: Int, val errorMsg: String) : Failure()
    class ServerError(val errorCode: Int, val errorMsg: String) : Failure()
    class UnCatchError(val exception: Exception) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
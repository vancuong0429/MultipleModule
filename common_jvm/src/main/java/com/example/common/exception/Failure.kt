package com.example.common.exception

sealed class Failure {
    sealed class NetworkConnection(val httpCode: Int, val errorMsg: String) : Failure()
    sealed class ServerError(val errorCode: Int, val errorMsg: String) : Failure()
    sealed class UnCatchError(val exception: Exception) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
package com.example.repository

import android.os.AsyncTask

data class Resource<out T> (val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, error)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR
    }
}
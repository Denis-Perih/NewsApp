package com.startandroid.newsapp.utils

data class Result<out T>(val status: Status, val data: T?) {

    companion object {
        fun <T> successData(data: T?): Result<T> {
            return Result(Status.SUCCESS, data)
        }

        fun <T> errorData(data: T?): Result<T> {
            return Result(Status.ERROR, data)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR
}

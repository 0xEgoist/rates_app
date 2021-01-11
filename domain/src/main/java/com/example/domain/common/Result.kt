package com.example.domain.common

import com.example.domain.exception.UseCaseException
import com.example.domain.exception.UseCaseExceptions

sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error(val code: Int, val message: String, val error: Exception? = null) :
        Result<Nothing>()
}

fun <T> Result<T>.getResult() = when (this) {
    is Result.Success<T> -> this.value
    is Result.Error -> throw UseCaseException(UseCaseExceptions.DEFAULT_EXCEPTION)
}
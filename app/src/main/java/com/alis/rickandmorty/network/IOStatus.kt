package com.alis.rickandmorty.network

import retrofit2.Response
import java.lang.Exception

data class IOStatus<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {
    companion object {
        fun <T> isSuccess(data: Response<T>): IOStatus<T> = IOStatus(status = Status.Success, data = data, exception = null)
        fun <T> isFailure(exception: Exception): IOStatus<T> = IOStatus(status = Status.Failure, data = null, exception = exception)
    }

    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }

    val isFailed: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !isFailed && this.data?.isSuccessful == true

    val body: T?
        get() = this.data?.body() ?: run { null }
}
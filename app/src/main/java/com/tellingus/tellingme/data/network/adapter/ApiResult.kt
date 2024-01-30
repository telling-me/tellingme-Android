package com.tellingus.tellingme.data.network.adapter

sealed class ApiResult<T> {
    class Success<T>(val data: T) : ApiResult<T>()
    class Failure<T>(val message: String) : ApiResult<T>()

    class NetworkError<T>(val throwable: Throwable) : ApiResult<T>()

}

fun<T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) {
        action.invoke(this.data)
    }
    return this
}

fun<T> ApiResult<T>.onFailure(action: (String) -> Unit): ApiResult<T> {
    if (this is ApiResult.Failure) {
        action.invoke(this.message)
    }
    return this
}

fun<T> ApiResult<T>.onNetworkError(action: (Throwable) -> Unit): ApiResult<T> {
    if (this is ApiResult.NetworkError) {
        action.invoke(this.throwable)
    }
    return this
}
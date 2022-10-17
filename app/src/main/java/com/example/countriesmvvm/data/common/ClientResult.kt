package com.example.countriesmvvm.data.common

sealed class ClientResult<out T> {
    data class Success<out T>(val data: T) : ClientResult<T>()
    data class Error(val exception: DataSourceException) : ClientResult<Nothing>()
    object Loading : ClientResult<Nothing>()
}

inline fun <T : Any> ClientResult<T>.onSuccess(action: (T) -> Unit): ClientResult<T> {
    if (this is ClientResult.Success) action(data)
    return this
}

inline fun <T : Any> ClientResult<T>.onError(action: (DataSourceException) -> Unit): ClientResult<T> {
    if (this is ClientResult.Error) action(exception)
    return this
}

inline fun <T : Any> ClientResult<T>.onLoading(action: () -> Unit): ClientResult<T> {
    if (this is ClientResult.Loading) action()
    return this
}
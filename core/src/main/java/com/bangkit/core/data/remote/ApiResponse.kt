package com.bangkit.core.data.remote

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val ignoredMessage: String) : ApiResponse<Nothing>()
}
package com.ashrafi.newsapp.domain.entity.common

sealed class ResultState<T> {
    data class Success<T>(
        val data: T?,
        val responseCode: Int,
    ) : ResultState<T>()

    data class Error<T>(val error: ErrorEntity) : ResultState<T>()
}


data class ErrorEntity(
    val errorMessage: String? = "",
    val data: Any? = null
)
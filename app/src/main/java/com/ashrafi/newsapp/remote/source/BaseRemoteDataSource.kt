package com.ashrafi.newsapp.remote.source

import com.ashrafi.newsapp.domain.entity.common.ResultState
import retrofit2.Response

interface BaseRemoteDataSource {
    suspend fun <T : Any> callApi(call: suspend() -> Response<T>): ResultState<T>
}
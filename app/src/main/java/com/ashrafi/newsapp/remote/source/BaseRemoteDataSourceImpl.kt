package com.ashrafi.newsapp.remote.source

import android.util.Log
import com.ashrafi.newsapp.domain.entity.common.ErrorEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import retrofit2.Response
import javax.inject.Inject


class BaseRemoteDataSourceImpl @Inject constructor(
) : BaseRemoteDataSource {

    override suspend fun <T : Any> callApi(call: suspend () -> Response<T>): ResultState<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                ResultState.Success(
                    data = response.body(),
                    responseCode = response.code(),
                )
            } else {
                ResultState.Error(
                    error = ErrorEntity(
                        "Error Occurred",
                    )
                )
            }
        } catch (ex: Throwable) {
            ResultState.Error(
                error = ErrorEntity(
                    "Error Occurred",
                )
            )
        }
    }
}
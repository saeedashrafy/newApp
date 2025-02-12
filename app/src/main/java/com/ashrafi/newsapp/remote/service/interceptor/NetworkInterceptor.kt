package com.ashrafi.newsapp.remote.service.interceptor

import com.ashrafi.newsapp.remote.di.RemoteModule
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class Interceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()

        newRequest.addHeader("Accept", "application/json")
        newRequest.addHeader("Authorization", RemoteModule.API_KEY)

        return chain.proceed(newRequest.build())
    }
}
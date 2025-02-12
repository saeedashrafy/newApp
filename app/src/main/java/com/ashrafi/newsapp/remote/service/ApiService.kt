package com.ashrafi.newsapp.remote.service

import com.ashrafi.newsapp.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface ApiService {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") fromDate: String,
        @Query("to") toDate: String,
        @Query("sortBy") sortBy: String,
        @Query("page") page: Int,
    ): Response<NewsDto>

}
package com.ashrafi.newsapp.data.source.remote

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState


interface NewsRemoteDataSource {
    suspend fun getNews(
        page: Int,
        queryType: String
    ): ResultState<NewsEntity>
}
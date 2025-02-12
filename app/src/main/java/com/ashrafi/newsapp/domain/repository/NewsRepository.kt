package com.ashrafi.newsapp.domain.repository

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.presentation.feature.enums.QueryType


interface NewsRepository {
    suspend fun getNews(
        page: Int,
        queryType: String
    ): ResultState<NewsEntity>
}
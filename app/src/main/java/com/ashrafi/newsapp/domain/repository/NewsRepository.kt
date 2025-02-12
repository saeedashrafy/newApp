package com.ashrafi.newsapp.domain.repository

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState


interface NewsRepository {
    suspend fun getNews(): ResultState<NewsEntity>
}
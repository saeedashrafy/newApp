package com.ashrafi.newsapp.domain.useCase

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState

interface NewsListUseCase {
    suspend fun getNewsList(
        page: Int,
        queryType: String,
    ): ResultState<NewsEntity>

    fun setSelectedNews(newsItem: NewsEntity.ArticleEntity)

    fun getSelectedNews(): NewsEntity.ArticleEntity?
}
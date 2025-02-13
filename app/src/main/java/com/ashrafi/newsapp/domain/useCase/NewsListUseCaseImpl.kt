package com.ashrafi.newsapp.domain.useCase

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsListUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsListUseCase {

    private var selectedNews: NewsEntity.ArticleEntity? = null

    override suspend fun getNewsList(
        page: Int,
        queryType: String
    ): ResultState<NewsEntity> {
        return newsRepository.getNews(
            page = page,
            queryType = queryType
        )
    }

    override fun setSelectedNews(newsItem: NewsEntity.ArticleEntity) {
        selectedNews = newsItem
    }

    override fun getSelectedNews(): NewsEntity.ArticleEntity? {
        return selectedNews
    }

}
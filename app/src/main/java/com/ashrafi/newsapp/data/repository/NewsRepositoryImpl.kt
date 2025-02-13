package com.ashrafi.newsapp.data.repository

import com.ashrafi.newsapp.data.source.remote.NewsRemoteDataSource
import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.domain.repository.NewsRepository
import com.ashrafi.newsapp.presentation.feature.enums.QueryType
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(
        page: Int,
        queryType: String,
        fromDate: String,
        toDate: String
    ): ResultState<NewsEntity> {
        return newsRemoteDataSource.getNews(
            page = page,
            queryType = queryType,
            fromDate = fromDate,
            toDate = toDate
        )
    }
}
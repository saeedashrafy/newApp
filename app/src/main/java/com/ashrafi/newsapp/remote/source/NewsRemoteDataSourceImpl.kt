package com.ashrafi.newsapp.remote.source

import com.ashrafi.newsapp.data.source.remote.NewsRemoteDataSource
import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.remote.dtoToEntity.map
import com.ashrafi.newsapp.remote.service.ApiService
import javax.inject.Inject


class NewsRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val baseRemoteDataSource: BaseRemoteDataSource
) : NewsRemoteDataSource {

    override suspend fun getNews(): ResultState<NewsEntity> {
        return baseRemoteDataSource.callApi {
            apiService.getNews(
                "apple",
                "2025-02-11",
                "2025-02-11",
                sortBy = "publishedAt",
                page = 1
            )
        }.map()
    }

}
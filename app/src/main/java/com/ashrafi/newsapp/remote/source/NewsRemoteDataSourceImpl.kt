package com.ashrafi.newsapp.remote.source

import com.ashrafi.newsapp.data.source.remote.NewsRemoteDataSource
import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.presentation.feature.enums.QueryType
import com.ashrafi.newsapp.remote.di.RemoteModule.Companion.SORT_BY
import com.ashrafi.newsapp.remote.dtoToEntity.map
import com.ashrafi.newsapp.remote.service.ApiService
import javax.inject.Inject


class NewsRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val baseRemoteDataSource: BaseRemoteDataSource
) : NewsRemoteDataSource {

    override suspend fun getNews(
        page: Int,
        queryType: String,
        fromDate: String,
        toDate: String
    ): ResultState<NewsEntity> {
        return baseRemoteDataSource.callApi {
            apiService.getNews(
                query = queryType,
                fromDate = fromDate,
                toDate = toDate,
                sortBy = SORT_BY,
                page = page
            )
        }.map()
    }

}
package com.ashrafi.newsapp.domain.useCase

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.presentation.feature.enums.QueryType

interface GetNewsUseCase {
    suspend operator fun invoke(
        page: Int,
        queryType: String,
    ): ResultState<NewsEntity>
}
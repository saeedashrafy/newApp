package com.ashrafi.newsapp.domain.useCase

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState

interface GetNewsUseCase {
    suspend operator fun invoke(
        page: Int,
        size: Int
    ): ResultState<NewsEntity>
}
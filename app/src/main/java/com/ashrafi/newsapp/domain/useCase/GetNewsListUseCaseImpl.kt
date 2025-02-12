package com.ashrafi.newsapp.domain.useCase

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : GetNewsUseCase {

    override suspend fun invoke(
        page: Int,
        size: Int
    ): ResultState<NewsEntity> {
        return newsRepository.getNews()
    }

}
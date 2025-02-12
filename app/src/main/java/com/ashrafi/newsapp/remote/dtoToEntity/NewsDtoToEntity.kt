package com.ashrafi.newsapp.remote.dtoToEntity

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.remote.dto.NewsDto


fun ResultState<NewsDto>.map(): ResultState<NewsEntity> {
    return when (this) {
        is ResultState.Success -> {
            ResultState.Success(
                data = NewsEntity(
                    status = data?.status,
                    totalResults = data?.totalResults,
                    articles = data?.articles?.map { item ->
                        NewsEntity.ArticleEntity(
                            newsSource = NewsEntity.NewsSourceEntity(
                                id = item.newsSource?.id,
                                name = item.newsSource?.name
                            ),
                            author = item.author,
                            title = item.title,
                            description = item.description,
                            url = item.url,
                            imageUrl = item.imageUrl,
                            publishedAt = item.publishedAt,
                            content = item.content
                        )
                    }
                ),
                responseCode = responseCode
            )
        }

        is ResultState.Error -> ResultState.Error(
            error = error
        )
    }
}
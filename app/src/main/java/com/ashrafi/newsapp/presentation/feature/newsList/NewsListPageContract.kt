package com.ashrafi.newsapp.presentation.feature.newsList

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.presentation.base.ViewEffect
import com.ashrafi.newsapp.presentation.base.ViewIntent
import com.ashrafi.newsapp.presentation.base.ViewState

data class NewsListState(
    val newsList: List<NewsEntity.ArticleEntity> = emptyList(),
    val showLoading: Boolean = false,
    val isFailure: Boolean = false
) : ViewState

sealed interface NewsListEvent : ViewIntent {
    data object GetNewsList : NewsListEvent
}

sealed interface NewsListEffect : ViewEffect
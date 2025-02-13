package com.ashrafi.newsapp.presentation.feature.newsList

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.presentation.base.ViewEffect
import com.ashrafi.newsapp.presentation.base.ViewIntent
import com.ashrafi.newsapp.presentation.base.ViewState
import com.ashrafi.newsapp.presentation.feature.enums.QueryType

data class NewsListState(
    val newsList: List<NewsEntity.ArticleEntity> = emptyList(),
    val tabList: List<QueryType> = emptyList(),
    val selectedTab: QueryType = QueryType.Microsoft,
    val showLoading: Boolean = true,
    val isFailure: Boolean = false
) : ViewState

sealed interface NewsListEvent : ViewIntent {
    data object GetNewsList : NewsListEvent
    data class OnTabChanged(val selectedTab: QueryType) : NewsListEvent
    data class OnItemClick(val item: NewsEntity.ArticleEntity) : NewsListEvent
}

sealed interface NewsListEffect : ViewEffect {
    data object NavigateToDetails : NewsListEffect
}
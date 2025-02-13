package com.ashrafi.newsapp.presentation.feature.newsDetails

import com.ashrafi.newsapp.domain.entity.NewsEntity
import com.ashrafi.newsapp.presentation.base.ViewEffect
import com.ashrafi.newsapp.presentation.base.ViewIntent
import com.ashrafi.newsapp.presentation.base.ViewState

data class NewsDetailsState(
    val newsItem: NewsEntity.ArticleEntity? = null
) : ViewState

sealed interface NewsDetailsEvent : ViewIntent {

}

sealed interface NewsDetailsEffect : ViewEffect
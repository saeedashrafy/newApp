package com.ashrafi.newsapp.presentation.feature.newsDetails

import androidx.lifecycle.viewModelScope
import com.ashrafi.newsapp.domain.useCase.NewsListUseCase
import com.ashrafi.newsapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailPageVM @Inject constructor(
    private val newsListUseCase: NewsListUseCase
) :
    BaseViewModel<NewsDetailsState, NewsDetailsEvent, NewsDetailsEffect>() {

    init {
        setSelectedNews()
    }

    override fun initialState(): NewsDetailsState {
        return NewsDetailsState()
    }

    override fun processIntents(viewIntent: NewsDetailsEvent) {
        viewModelScope.launch {
            when (viewIntent) {

                else -> {}
            }
        }
    }


    private fun setSelectedNews() {
        updateState {
            it.copy(
                newsItem = newsListUseCase.getSelectedNews()
            )
        }
    }

}
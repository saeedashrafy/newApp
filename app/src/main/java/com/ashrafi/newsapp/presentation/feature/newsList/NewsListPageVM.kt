package com.ashrafi.newsapp.presentation.feature.newsList

import androidx.lifecycle.viewModelScope
import com.ashrafi.newsapp.core.common.disptacher.DispatcherAnnotations
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.domain.useCase.GetNewsUseCase
import com.ashrafi.newsapp.presentation.base.BaseViewModel
import com.ashrafi.newsapp.presentation.feature.enums.QueryType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListPageVM @Inject constructor(
    @DispatcherAnnotations.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel<NewsListState, NewsListEvent, NewsListEffect>() {

    private var pageNumber: Int = 1
    private val pageSize: Int = 100
    private var canPaginate: Boolean = true
    private var getNewsListJob: Job? = null


    override fun initialState(): NewsListState {
        return NewsListState(
            tabList = makeTabItems()
        )
    }

    override fun processIntents(viewIntent: NewsListEvent) {
        viewModelScope.launch {
            when (viewIntent) {
                NewsListEvent.GetNewsList -> getNewsList()
                is NewsListEvent.OnTabChanged -> onTabChanged(viewIntent.selectedTab)
            }
        }
    }

    private fun makeTabItems(): List<QueryType> {
        return QueryType.entries
    }

    private fun onTabChanged(selectedTab: QueryType) {
        updateState {
            it.copy(
                selectedTab = selectedTab
            )
        }

        pageNumber = 1
        updateState {
            it.copy(
                newsList = emptyList()
            )
        }
        getNewsList()
    }


    private fun getNewsList() {
        getNewsListJob?.cancel()

        getNewsListJob = viewModelScope.launch(ioDispatcher) {
            if (canPaginate) {
                updateState {
                    it.copy(
                        showLoading = true,
                        isFailure = false
                    )
                }

                getNewsUseCase(
                    page = pageNumber,
                    queryType = getState().selectedTab.name
                ).let { result ->

                    when (result) {
                        is ResultState.Success -> {
                            pageNumber++

                            /// doc: add to previous list
                            val dataList = getState().newsList.toMutableList()
                            result.data?.articles?.let { dataList.addAll(it) }

                            canPaginate = (result.data?.totalResults ?: 0) > pageNumber * pageSize

                            updateState {
                                it.copy(
                                    newsList = dataList,
                                    showLoading = canPaginate
                                )
                            }
                        }

                        is ResultState.Error -> {
                            updateState {
                                it.copy(
                                    showLoading = false,
                                    isFailure = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}
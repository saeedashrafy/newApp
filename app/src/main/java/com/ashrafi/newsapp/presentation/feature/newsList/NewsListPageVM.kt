package com.ashrafi.newsapp.presentation.feature.newsList

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ashrafi.newsapp.core.common.disptacher.DispatcherAnnotations
import com.ashrafi.newsapp.domain.entity.common.ResultState
import com.ashrafi.newsapp.domain.useCase.GetNewsUseCase
import com.ashrafi.newsapp.presentation.base.BaseViewModel
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

    private var pageNumber: Int = 0
    private val pageSize: Int = 50
    private var canPaginate: Boolean = true
    private var getNewsListJob: Job? = null


    override fun initialState(): NewsListState {
        return NewsListState()
    }

    override fun sendEvent(viewIntent: NewsListEvent) {
        viewModelScope.launch {
            when (viewIntent) {
                NewsListEvent.GetNewsList -> getNewsList()
            }
        }
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
                    size = pageSize
                ).let { result ->

                    when (result) {
                        is ResultState.Success -> {
                            pageNumber++

                            /// doc: add to previous list
                            val dataList = getState().newsList.toMutableList()
                            result.data?.articles?.let { dataList.addAll(it) }

                            canPaginate = result.data?.totalResults == pageSize

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
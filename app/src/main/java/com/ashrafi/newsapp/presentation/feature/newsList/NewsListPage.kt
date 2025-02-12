package com.ashrafi.newsapp.presentation.feature.newsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ashrafi.newsapp.R
import com.ashrafi.newsapp.core.ui.components.BorderButton
import com.ashrafi.newsapp.core.ui.components.FailureView
import com.ashrafi.newsapp.core.ui.components.NoDataView
import com.ashrafi.newsapp.core.ui.theme.Purple40
import com.ashrafi.newsapp.domain.entity.NewsEntity

@Composable
fun NewsListPage(
    viewModel: NewsListPageVM = hiltViewModel(),
    onClickDetails: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    NewsPaginationAdapter(
        data = state.newsList,
        isFailure = state.isFailure,
        showLoading = state.showLoading,
        modifier = Modifier.fillMaxSize(),
        onFetchMore = {
            viewModel.sendEvent(NewsListEvent.GetNewsList)
        }
    )
}


@Composable
private fun NewsPaginationAdapter(
    data: List<NewsEntity.ArticleEntity>,
    isFailure: Boolean,
    showLoading: Boolean,
    modifier: Modifier = Modifier,
    onFetchMore: () -> Unit,
) {
    val lazyColumnListState = rememberLazyListState()

    val shouldStartPaginate by remember {
        derivedStateOf {
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastIndex + lazyColumnListState.firstVisibleItemIndex) + 1 >=
                    ((lazyColumnListState.layoutInfo.totalItemsCount))
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate) {
        if (shouldStartPaginate)
            onFetchMore()
    }

    LazyColumn(
        modifier = modifier,
        state = lazyColumnListState,
        contentPadding = PaddingValues(
            bottom = 10.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(items = data) { _, item ->
            NewsItemView(
                modifier = Modifier.fillMaxWidth(),
                item = item
            )
        }

        if (showLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        color = Purple40
                    )
                }
            }

        } else if (isFailure) {
            item {
                if (data.isEmpty()) {
                    FailureView(
                        modifier = Modifier.fillMaxSize(),
                        onClickRetryButton = {
                            onFetchMore()
                        }
                    )

                } else {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BorderButton(
                            title = stringResource(id = R.string.retry),
                            onClick = { onFetchMore() },
                            borderAndTitleColor = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                        )
                    }
                }
            }

        } else if (data.isEmpty()) {
            item {
                NoDataView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                )
            }
        }
    }
}

@Composable
private fun NewsItemView(
    modifier: Modifier,
    item: NewsEntity.ArticleEntity
) {
    Row(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imageUrl)
                .placeholder(R.drawable.ic_news)
                .error(R.drawable.ic_news)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = item.title ?: "",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

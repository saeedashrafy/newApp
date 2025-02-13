package com.ashrafi.newsapp.presentation.feature.newsDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ashrafi.newsapp.R
import com.ashrafi.newsapp.core.ui.theme.Gray
import com.ashrafi.newsapp.utils.DateTimeUtils

@Composable
fun NewsDetailsPage(
    viewModel: NewsDetailPageVM = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    NewsDetailsScreen(
        state = state,
        sendEvent = viewModel::setEvent
    )
}

@Composable
private fun NewsDetailsScreen(
    state: NewsDetailsState,
    sendEvent: (NewsDetailsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Gray),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.newsItem?.imageUrl)
                    .placeholder(R.drawable.ic_news)
                    .error(R.drawable.ic_news)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        color = Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(4.dp)
                    .align(Alignment.BottomStart),
                text = state.newsItem?.newsSource?.name ?: "",
                fontSize = 12.sp,
                maxLines = 1,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = state.newsItem?.author ?: "",
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = DateTimeUtils.formatDate(state.newsItem?.publishedAt ?: ""),
                fontSize = 12.sp,
                maxLines = 1
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 6.dp),
            text = state.newsItem?.title ?: "",
            maxLines = 4,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 6.dp),
            text = state.newsItem?.content ?: "",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            fontSize = 14.sp
        )


    }
}
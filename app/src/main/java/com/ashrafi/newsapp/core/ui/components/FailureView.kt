package com.ashrafi.newsapp.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ashrafi.newsapp.R


@Composable
fun FailureView(
    modifier: Modifier = Modifier,
    message: String? = stringResource(id = R.string.errorOccurred),
    onClickRetryButton: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = message ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        onClickRetryButton?.let {

            BorderButton(
                modifier = Modifier.padding(top = 16.dp),
                title = stringResource(id = R.string.retry),
                onClick = onClickRetryButton,
                borderAndTitleColor = MaterialTheme.colorScheme.onSurface.copy(0.5f)
            )
        }
    }

}
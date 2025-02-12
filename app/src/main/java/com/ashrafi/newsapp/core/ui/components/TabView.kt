package com.ashrafi.newsapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TabView(
    title: String,
    isSelected: Boolean,
    onTabClick: () -> Unit,
    modifier: Modifier = Modifier,
    titleColor: Color = if (isSelected)
        MaterialTheme.colorScheme.primary
    else
        MaterialTheme.colorScheme.onSurface
) {
    Tab(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
            .addFocusedBorder(isSelected = isSelected),
        text = {
            Text(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = title,
                color = titleColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                style = MaterialTheme.typography.bodySmall
            )
        },
        selected = isSelected,
        onClick = {
            onTabClick()
        }
    )
}

package com.ashrafi.newsapp.core.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(navigationIcon: @Composable (() -> Unit)?) {
    TopAppBar(
        title = {
            Text(
                text = "News Application",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        navigationIcon = navigationIcon ?: {})
}
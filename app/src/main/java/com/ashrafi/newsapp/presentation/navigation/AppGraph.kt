package com.ashrafi.newsapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ashrafi.newsapp.presentation.feature.newsDetails.NewsDetailsPage
import com.ashrafi.newsapp.presentation.feature.newsList.NewsListPage


@Composable
fun AppGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.NewsListDestination.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(Destinations.NewsListDestination.route) {
            NewsListPage(navController = navController)
        }

        composable(Destinations.NewsDetailsDestination.route) {
            NewsDetailsPage()
        }
    }
}
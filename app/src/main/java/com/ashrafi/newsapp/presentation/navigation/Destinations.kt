package com.ashrafi.newsapp.presentation.navigation

sealed class Destinations(val route: String) {
    data object NewsListScreen : Destinations("newsList")
    data object NewsDetailsScreen : Destinations("newsDetails")
}
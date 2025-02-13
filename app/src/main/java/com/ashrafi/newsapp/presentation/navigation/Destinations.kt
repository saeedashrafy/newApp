package com.ashrafi.newsapp.presentation.navigation

sealed class Destinations(val route: String) {
    data object NewsListDestination : Destinations("newsList")
    data object NewsDetailsDestination : Destinations("newsDetails")
}
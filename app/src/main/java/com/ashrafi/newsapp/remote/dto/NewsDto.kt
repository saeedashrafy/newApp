package com.ashrafi.newsapp.remote.dto

import com.google.gson.annotations.SerializedName


data class NewsDto(
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: Int?,
    @SerializedName("articles") val articles: List<ArticleDto>?,
) {
    data class ArticleDto(
        @SerializedName("source") val newsSource: NewsSourceDto?,
        @SerializedName("author") val author: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("url") val url: String?,
        @SerializedName("urlToImage") val imageUrl: String?,
        @SerializedName("publishedAt") val publishedAt: String,
        @SerializedName("content") val content: String?,
    )

    data class NewsSourceDto(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String
    )
}
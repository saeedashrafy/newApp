package com.ashrafi.newsapp.domain.entity

data class NewsEntity(
    val status: String?,
    val totalResults: Int?,
    val articles: List<ArticleEntity>?,
) {
    data class ArticleEntity(
        val newsSource: NewsSourceEntity?,
        val author: String?,
        val title: String?,
        val description: String?,
        val url: String?,
        val imageUrl: String?,
        val publishedAt: String,
        val content: String?,
    )

    data class NewsSourceEntity(
        val id: String?,
        val name: String?
    )
}
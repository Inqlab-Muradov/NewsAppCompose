package com.example.newsappcompose.data.dto

data class NewsDto(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)
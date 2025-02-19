package com.example.newsappcompose.presentation.home

import com.example.newsappcompose.domain.model.NewsModel

data class HomeUiState(
    val vsjNewsList: List<NewsModel>? = emptyList(),
    val usNewsList: List<NewsModel>? = emptyList(),
    val errorMessage: String? = null,
    val loading: Boolean = false,
    val searchQuery: String = "",
    val filteredUsNews: List<NewsModel>? = emptyList()
)


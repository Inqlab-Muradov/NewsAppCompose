package com.example.newsappcompose.presentation.favourites

import com.example.newsappcompose.domain.model.NewsModel

data class FavouritesUiState(
    val savedList: List<NewsModel> = emptyList(),
    val error: String? = null,
    val loading: Boolean? = null,
)

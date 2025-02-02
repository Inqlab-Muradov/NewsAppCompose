package com.example.newsappcompose.presentation.home

import com.example.newsappcompose.domain.Model.NewsModel

data class HomeUiState (
    val vsjNewsList  : List<NewsModel>?= emptyList(),
    val errorMessage:String? = null
)


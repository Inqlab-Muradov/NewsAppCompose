package com.example.newsappcompose.presentation.home

sealed class HomeIntent {
    data class SearchedQueryChanged(val query: String) : HomeIntent()
}
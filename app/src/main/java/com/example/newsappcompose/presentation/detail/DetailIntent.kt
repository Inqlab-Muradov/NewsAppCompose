package com.example.newsappcompose.presentation.detail

import com.example.newsappcompose.domain.model.NewsModel

sealed class DetailIntent {
   data class OnAddClick( val newsModel: NewsModel) : DetailIntent()
   data class DeleteSavedItem(val newsModel: NewsModel) : DetailIntent()
}
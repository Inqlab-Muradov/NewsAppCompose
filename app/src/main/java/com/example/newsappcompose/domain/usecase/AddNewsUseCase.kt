package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.domain.repository.Repository
import javax.inject.Inject

class AddNewsUseCase @Inject constructor(
    private val repository:Repository
) {
    suspend fun addNews(newsModel:NewsModel){
        repository.addNewsToFav(newsModel)
    }
}
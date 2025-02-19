package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.domain.repository.Repository
import javax.inject.Inject

class DeleteSavedItemUseCase @Inject constructor(
    private val repository: Repository
){

    suspend fun deleteSavedItem(newsModel: NewsModel){
        repository.deleteSavedItem(newsModel)
    }
}
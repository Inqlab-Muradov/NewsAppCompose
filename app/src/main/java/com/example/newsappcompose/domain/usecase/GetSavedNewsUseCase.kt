package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.domain.repository.Repository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(
    val repository: Repository
) {
    suspend fun getSavedNews(
        onSuccess: (savedList : List<NewsModel>) -> Unit,
        onError: (error:Exception) -> Unit,
        onLoading: () -> Unit,
        onFinished: () -> Unit
    ) {
        onLoading()
        try {
            val savedList = repository.getSavedNews()
            onSuccess(savedList)
        }
        catch (e:Exception){
            onError(e)
        }
        finally {
            onFinished()
        }
    }
}
package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.data.dto.NewsDto
import com.example.newsappcompose.domain.repository.Repository
import javax.inject.Inject

class GetVsjNewsUseCase @Inject constructor(
    val repository: Repository
) {
    suspend fun getVsjNews(
        onSuccess: (result: NewsDto?) -> Unit,
        onError: (error: Exception?) -> Unit,
        onLoading: () -> Unit,
        onFinished : ()->Unit
    ) {
        onLoading()
        try {
            val result = repository.getVsjNews()
            onSuccess(result)
        } catch (e: Exception) {
            onError(e)
        } finally {
            onFinished()
        }
    }

}
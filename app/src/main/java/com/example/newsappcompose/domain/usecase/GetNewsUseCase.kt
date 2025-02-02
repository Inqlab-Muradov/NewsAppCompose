package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.data.dto.NewsDto
import com.example.newsappcompose.domain.repository.Repository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    val repository: Repository
) {
    suspend fun execute(
        onSuccess: (result: NewsDto?) -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        try {
            val result = repository.getVsjNews()
            onSuccess(result)
        } catch (e: Exception) {
            onError(e)
        }
    }
}
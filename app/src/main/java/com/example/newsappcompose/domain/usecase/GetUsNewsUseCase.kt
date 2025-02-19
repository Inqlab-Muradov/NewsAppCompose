package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.data.dto.NewsDto
import com.example.newsappcompose.domain.repository.Repository
import kotlinx.coroutines.CancellationException
import javax.inject.Inject


class GetUsNewsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getUsNews(
        onSuccess: (result: NewsDto?) -> Unit,
        onError: (error: Exception?) -> Unit,
        onLoading: () -> Unit = {},
        onFinished: () -> Unit = {},
    ) {
        onLoading()
        try {
            val result = repository.getUsNews()
            onSuccess(result)
        }
        catch (e: CancellationException){
            throw e
        }
        catch (e: Exception) {
            onError(e)
        } finally {
            onFinished()
        }
    }
}
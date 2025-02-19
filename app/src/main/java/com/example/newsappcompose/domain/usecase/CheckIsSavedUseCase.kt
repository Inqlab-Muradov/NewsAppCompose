package com.example.newsappcompose.domain.usecase

import android.util.Log
import com.example.newsappcompose.domain.repository.Repository
import javax.inject.Inject

class CheckIsSavedUseCase @Inject constructor(
    private val repository:Repository
) {
    suspend fun checkIsSaved(
        urlToImage:String,
        onSuccess:()->Unit,
        onFailure:()->Unit)
    {
        val result = repository.checkSavedNews(urlToImage)
        if (result>=1) onSuccess() else onFailure()
    }
}
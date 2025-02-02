package com.example.newsappcompose.domain.repository

import com.example.newsappcompose.data.api.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    val api: ApiService
) {
    suspend fun getVsjNews() = api.getWsjNews()
}
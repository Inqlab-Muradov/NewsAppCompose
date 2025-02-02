package com.example.newsappcompose.data.api

import com.example.newsappcompose.common.API_KEY
import com.example.newsappcompose.data.dto.NewsDto

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getWsjNews(
        @Query("domains") domains:String = "wsj.com", @Query("apiKey") apikey : String = API_KEY
    ) : NewsDto
}
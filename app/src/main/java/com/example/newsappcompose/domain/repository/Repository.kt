package com.example.newsappcompose.domain.repository

import com.example.newsappcompose.data.dto.NewsDto
import com.example.newsappcompose.domain.model.NewsModel

interface Repository {
    suspend fun getUsNews() : NewsDto
    suspend fun getVsjNews() : NewsDto
    suspend fun addNewsToFav(newsModel: NewsModel)
    suspend fun getSavedNews() :List<NewsModel>
    suspend fun checkSavedNews(urlToImage:String) : Int
    suspend fun deleteSavedItem(newsItem:NewsModel)
}
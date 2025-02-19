package com.example.newsappcompose.data.repo

import com.example.newsappcompose.data.api.ApiService
import com.example.newsappcompose.data.dto.NewsDto
import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val api: ApiService,
    val dao : NewsDao
) : Repository {
    override suspend fun getUsNews(): NewsDto {
      return  api.getUsNews()
    }

    override suspend fun getVsjNews() : NewsDto {
        return api.getWsjNews()
    }

    override suspend fun addNewsToFav(newsModel: NewsModel) {
        dao.addNews(newsModel)
    }

    override suspend fun getSavedNews() = dao.getSavedNews()

    override suspend fun checkSavedNews(urlToImage: String): Int {
        return dao.checkSavedNews(urlToImage)
    }

    override suspend fun deleteSavedItem(newsItem: NewsModel) {
       dao.deleteSaveItems(newsItem)
    }

}
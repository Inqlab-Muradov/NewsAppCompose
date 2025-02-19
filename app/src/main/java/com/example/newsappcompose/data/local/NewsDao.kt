package com.example.newsappcompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.newsappcompose.domain.model.NewsModel
@Dao
interface NewsDao {

    @Upsert
    suspend fun addNews(newsModel: NewsModel)

    @Query("SELECT*FROM  savednews")
    suspend fun getSavedNews() : List<NewsModel>

    @Query("SELECT COUNT(*) FROM savednews WHERE urlToImage = :urlToImage")
    suspend fun checkSavedNews(urlToImage:String) : Int

    @Delete
    suspend fun deleteSaveItems(newsModel:NewsModel)
}
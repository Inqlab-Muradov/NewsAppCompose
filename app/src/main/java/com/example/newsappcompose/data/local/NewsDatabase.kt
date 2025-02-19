package com.example.newsappcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsappcompose.domain.model.NewsModel

@Database(entities = [NewsModel::class], version = 2)
abstract class  NewsDatabase : RoomDatabase() {
    abstract fun getDao() : NewsDao
}
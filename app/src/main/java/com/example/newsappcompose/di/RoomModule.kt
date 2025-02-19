package com.example.newsappcompose.di

import android.content.Context
import androidx.room.Room
import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun injectRoom(@ApplicationContext context: Context) : NewsDatabase {
        return Room.databaseBuilder(context,NewsDatabase::class.java,"NewsDb").build()
    }

    @Provides
    @Singleton
    fun injectDao(newsDatabase: NewsDatabase): NewsDao{
        return newsDatabase.getDao()
    }

}
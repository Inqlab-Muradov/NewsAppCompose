package com.example.newsappcompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "SavedNews")
@Serializable
data class NewsModel(
    val source: String,
    val description:String,
    @PrimaryKey
    val title:String,
    val content:String,
    val urlToImage:String,
    val author:String,
    val publishedAt :String
)

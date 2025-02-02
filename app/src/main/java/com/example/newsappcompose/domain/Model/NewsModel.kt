package com.example.newsappcompose.domain.Model

import com.example.newsappcompose.data.dto.Source

data class NewsModel(
    val source: Source?,
    val description:String?,
    val title:String?,
    val content:String?,
    val urlToImage:String?,
    val author:String?
)

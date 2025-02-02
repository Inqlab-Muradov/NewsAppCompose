package com.example.newsappcompose.data.mapper

import com.example.newsappcompose.data.dto.Article
import com.example.newsappcompose.data.dto.NewsDto
import com.example.newsappcompose.domain.Model.NewsModel

fun List<Article>.toNewsModel() = map {
    NewsModel(
        source = it.source,
        description = it.description,
        title = it.title,
        content = it.content,
        author = it.author,
        urlToImage = it.urlToImage
    )
}

//class GetNewsUseCase {
//    fun execute(
//        onSuccess: (result:NewsDto) -> Unit,
//        onError: (e:Exception) -> Unit,
//        onFinished:
//    ) {
//        try {
//           val result =  RemoteDataSourceImpl().getNews()
//            onSuccess(result)
//        } catch (e:Exception) {
//            onError(e)
//        }
//        finally {
//            onFinished()
//        }
//    }
//}
//
//class VM {
//    fun getNews() {
//        GetNewsUseCase().execute(
//            onSuccess = {result->
//
//            },
//            onError = {exception->
//
//            }
//        )
//    }
//}
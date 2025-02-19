package com.example.newsappcompose.data.mapper

import com.example.newsappcompose.data.dto.Article
import com.example.newsappcompose.domain.model.NewsModel

fun List<Article>.toNewsModel() = map {
    NewsModel(
        source = it.source?.name.orEmpty(),
        description = it.description.orEmpty(),
        title = it.title.orEmpty(),
        content = it.content.orEmpty(),
        author = it.author.orEmpty(),
        urlToImage = it.urlToImage.orEmpty(),
        publishedAt = it.publishedAt.orEmpty()
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
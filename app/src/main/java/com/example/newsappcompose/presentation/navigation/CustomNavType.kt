package com.example.newsappcompose.presentation.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.newsappcompose.domain.model.NewsModel
import kotlinx.serialization.json.Json

object CustomNavType {
    val newsType =object:NavType<NewsModel>(isNullableAllowed = false){
        override fun get(bundle: Bundle, key: String): NewsModel? {
            return Json.decodeFromString(bundle.getString(key) ?:return null)
        }
        override fun parseValue(value: String): NewsModel {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: NewsModel): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: NewsModel) {
            bundle.putString(key,Json.encodeToString(value))
        }

    }
}
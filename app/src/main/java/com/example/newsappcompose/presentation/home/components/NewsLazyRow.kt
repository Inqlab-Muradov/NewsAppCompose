package com.example.newsappcompose.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.newsappcompose.R


@Composable
fun NewsRow() {
    LazyRow(modifier = Modifier.padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(newsList) { newsItem ->
            LazyRowItem(newsItem = newsItem)
        }
    }
}

@Composable
fun LazyRowItem(newsItem: NewsItem) {
    val itemWidth = LocalConfiguration.current.screenWidthDp.dp
    Box(modifier = Modifier.padding(end = 16.dp)) {
        Image(
            modifier = Modifier.width(itemWidth*0.8f), contentScale = ContentScale.Crop,
            painter = painterResource(id = newsItem.image), contentDescription = null
        )
        Box(
            modifier = Modifier
                .padding(18.dp)
                .clip(
                    RoundedCornerShape(32.dp)
                )
                .alpha(alpha = 0.6f)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = newsItem.section, style = androidx.compose.ui.text.TextStyle(
                    fontSize = 11.sp,
                    color = Color.Black
                )
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            text = newsItem.description, style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            )
        )
    }
}

val newsList: List<NewsItem> = listOf(
    NewsItem(R.drawable.image, "Description", "section"),
    NewsItem(R.drawable.image, "Description", "section"),
    NewsItem(R.drawable.image, "Description", "section")
)

data class NewsItem(
    val image: Int,
    val description: String,
    val section: String
)


@Preview(showBackground = true)
@Composable
private fun SetPreview() {
    NewsRow()
}
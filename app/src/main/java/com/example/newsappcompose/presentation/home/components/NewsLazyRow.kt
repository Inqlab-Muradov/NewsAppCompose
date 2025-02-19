package com.example.newsappcompose.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.presentation.home.HomeUiState
import com.example.newsappcompose.presentation.navigation.ScreenRoute


@Composable
fun NewsRow(homeUiState: HomeUiState,onNewsClick: (rowItem: NewsModel) -> Unit) {
    LazyRow(
        modifier = Modifier.padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        homeUiState.vsjNewsList?.let {
            val list = it
            items(list) { newsItem ->
                LazyRowItem(newsItem, onNewsClick)
            }
        }
    }
}

@Composable
fun LazyRowItem(
    rowItem: NewsModel,
    onNewsClick: (rowItem:NewsModel) ->Unit
) {
    val itemWidth = LocalConfiguration.current.screenWidthDp.dp
    Box(
        modifier = Modifier
            .width(itemWidth * 0.82f)
            .padding(end = 16.dp)
            .fillMaxHeight(0.27f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .clickable {
                    onNewsClick(rowItem)
                },
            contentScale = ContentScale.Crop,
            model = rowItem.urlToImage,
            contentDescription = null
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
                text = rowItem.source, style = androidx.compose.ui.text.TextStyle(
                    fontSize = 11.sp,
                    color = Color.Black
                )
            )

        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            text = rowItem.title, style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SetPreview() {
}
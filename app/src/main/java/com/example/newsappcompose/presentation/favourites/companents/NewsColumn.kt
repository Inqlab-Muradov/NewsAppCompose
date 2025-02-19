package com.example.newsappcompose.presentation.favourites.companents


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsappcompose.R
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.presentation.favourites.FavouritesUiState
import com.example.newsappcompose.presentation.home.HomeUiState

@Composable
fun FavNewsColumn(favouritesUiState: FavouritesUiState, onClick: (newsItem: NewsModel) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 21.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(favouritesUiState.savedList){item->
            LazyColumnItem(item,onClick)
        }
    }
}

@Composable
fun LazyColumnItem(newsColumnItem: NewsModel, onClick: (newsItem: NewsModel) -> Unit) {
    Box(
        modifier = Modifier
            .padding(end = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .clickable {
                onClick(newsColumnItem)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(32.dp)
                        )
                        .alpha(alpha = 0.6f)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = newsColumnItem.source, style = TextStyle(
                            fontSize = 11.sp,
                            color = Color.Black
                        )
                    )

                }
                Text(
                    modifier = Modifier.padding(end = 12.dp),
                    text = newsColumnItem.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700)
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = newsColumnItem.publishedAt.substring(0, 10), style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500),
                            color = colorResource(id = R.color.searchPlaceHolder)
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.padding(end = 12.dp),
                        text = newsColumnItem.author, style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(700)
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            AsyncImage(
                modifier = Modifier
                    .weight(0.3f)
                    .clip(RoundedCornerShape(18.dp))
                    .aspectRatio(1.25f),
                contentScale = ContentScale.Crop,
                model = newsColumnItem.urlToImage,
                contentDescription = null
            )
        }
    }
}


package com.example.newsappcompose.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappcompose.R

@Composable
fun NewsColumn() {
    LazyColumn (modifier = Modifier.padding(end = 24.dp, top = 24.dp)){
        items(newsColumnItem) { item ->
            LazyColumnItem(newsColumnItem = item)
        }
    }
}

@Composable
fun LazyColumnItem(newsColumnItem: NewsColumnItem) {
    Box(
        modifier = Modifier.padding(bottom = 24.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.weight(3f),
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
                        text = newsColumnItem.section, style = TextStyle(
                            fontSize = 11.sp,
                            color = Color.Black
                        )
                    )
                }
                Text(
                    modifier = Modifier.padding(end = 12.dp),
                    text = newsColumnItem.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700)
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = newsColumnItem.date, style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500),
                            color = colorResource(id = R.color.searchPlaceHolder)
                        )
                    )
                    Text(
                        modifier = Modifier.padding(end = 12.dp),
                        text = newsColumnItem.tittle, style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(700)
                        )
                    )
                }
            }
            Image(
                modifier = Modifier.weight(1f), contentScale = ContentScale.Crop,
                painter = painterResource(id = newsColumnItem.image), contentDescription = null
            )
        }
    }
}

data class NewsColumnItem(
    val image: Int,
    val description: String,
    val date: String,
    val tittle: String,
    val section: String
)

val newsColumnItem: List<NewsColumnItem> = listOf(
    NewsColumnItem(
        image = R.drawable.newsimage,
        "Factbox: Who is still buying Russian crude oil?",
        "2022-03-26",
        "Jennifer Wars",
        "Finance"),
    NewsColumnItem(
        image = R.drawable.newsimage,
        "Factbox: Who is still buying Russian crude oil?",
        "2022-03-26",
        "Jennifer Wars",
        "Finance"),
    NewsColumnItem(
        image = R.drawable.newsimage,
        "Factbox: Who is still buying Russian crude oil?",
        "2022-03-26",
        "Jennifer Wars",
        "Finance"),
)



@Preview(showBackground = true)
@Composable
private fun SetPreview() {
    NewsColumn()
}
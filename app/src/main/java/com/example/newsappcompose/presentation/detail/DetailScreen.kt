package com.example.newsappcompose.presentation.detail


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsappcompose.R
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.presentation.detail.companents.DropDownMenu

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    newsItem: NewsModel,
    onBackClick: () -> Unit,
    onAddSaveClick : (NewsModel)->Unit,
    deleteSavedClick : (NewsModel)->Unit,
    detailUiState: DetailUiState
) {

    Box(
        modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(modifier.fillMaxHeight(0.3f)) {
            AsyncImage(
                model = newsItem.urlToImage,
                contentDescription = null,
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.fillMaxWidth().padding(24.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .clip(
                            RoundedCornerShape(32.dp)
                        )
                        .background(color = colorResource(R.color.sectionBoxBg))
                        .clickable {
                            onBackClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(R.drawable.back), null)
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = newsItem.title, style = TextStyle(
                                fontSize = 11.sp,
                                color = Color.Black
                            ), maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                DropDownMenu(newsItem = newsItem, onAddSaveClick = onAddSaveClick, detailUiState = detailUiState, deleteSavedClick = deleteSavedClick)
            }

        }
        Box(
            modifier
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .background(Color.White)
        ) {
            Column(
                modifier
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .clip(
                            RoundedCornerShape(32.dp)
                        )
                        .background(color = colorResource(R.color.sectionBoxBg)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = newsItem.source, style = TextStyle(
                            fontSize = 11.sp,
                            color = Color.Black
                        )
                    )
                }
                Text(
                    modifier = Modifier,
                    text = newsItem.title,
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight(700),
                        color = Color.Black
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = newsItem.author,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(700),
                            color = colorResource(R.color.authorNameColor)
                        )
                    )
                }
                Text(
                    text = newsItem.description,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(700),
                        color = Color.Black
                    ),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(vertical = 24.dp),
                    text = newsItem.content,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = colorResource(R.color.newsContextColor)
                    )
                )
            }
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    DropDownMenu()
//}
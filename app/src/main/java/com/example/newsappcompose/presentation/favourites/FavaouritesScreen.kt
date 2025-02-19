package com.example.newsappcompose.presentation.favourites

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.presentation.favourites.companents.FavNewsColumn
import com.example.newsappcompose.presentation.home.components.SetBottomBar
import com.example.newsappcompose.presentation.home.components.TopBar
import com.example.newsappcompose.presentation.navigation.ScreenRoute

@Composable
fun SetFavScreen(
    modifier: Modifier,
    onNavigateTo: (ScreenRoute) -> Unit,
    selectedIndex: Int,
    onSelectedIndexChanged: (Int) -> Unit,
    favouritesUiState: FavouritesUiState,
    onNewsClick: (NewsModel) -> Unit
) {
    Log.d("sabo",favouritesUiState.savedList.toString())
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { SetBottomBar(onNavigateTo, selectedIndex, onSelectedIndexChanged) },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).background(Color.White)) {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                    text = "29.01.2025", style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Gray
                    )
                )
                FavNewsColumn(favouritesUiState,onNewsClick)
            }
        }
    )

}

//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    SetFavScreen(modifier = Modifier)
//}
package com.example.newsappcompose.presentation.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.newsappcompose.presentation.home.components.SetBottomBar
import com.example.newsappcompose.presentation.navigation.ScreenRoute

@Composable
fun SetFavScreen(modifier: Modifier,
                 onNavigateTo: (ScreenRoute) -> Unit,
                 selectedIndex:Int,
                 onSelectedIndexChanged:(Int)->Unit) {
    Scaffold(
        bottomBar = { SetBottomBar(onNavigateTo,selectedIndex,onSelectedIndexChanged) },
        content = { paddingValues ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Favourites", style = TextStyle(
                        color = Color.Blue,
                        fontSize = 20.sp
                    )
                )
            }
        }
    )

}

//@Preview(showBackground = true)
//@Composable
//private fun Preview() {
//    SetFavScreen(modifier = Modifier)
//}
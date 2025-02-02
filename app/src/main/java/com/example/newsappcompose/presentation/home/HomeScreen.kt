package com.example.newsappcompose.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsappcompose.presentation.home.components.NewsColumn
import com.example.newsappcompose.presentation.home.components.NewsRow
import com.example.newsappcompose.presentation.home.components.SearchBar
import com.example.newsappcompose.presentation.home.components.SetBottomBar
import com.example.newsappcompose.presentation.home.components.TopBar
import com.example.newsappcompose.presentation.navigation.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigationTo: (ScreenRoute)->Unit,
    selectedIndex:Int,
    onSelectedIndexChanged:(Int)->Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.homeUiState.collectAsStateWithLifecycle()
    Log.d("Sabo","$state")
    Scaffold(
        modifier = modifier,
        topBar = {TopBar()},
        bottomBar= { SetBottomBar(onNavigationTo,selectedIndex,onSelectedIndexChanged) },
        content = { paddingValues ->
            Column(modifier= Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 24.dp)
                .navigationBarsPadding()
            ) {
                Text(modifier = Modifier.padding(vertical = 16.dp),
                    text = "29.01.2025", style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Gray
                    ))
                NewsRow()
                SearchBar()
                NewsColumn()
            }
        }
    )
}




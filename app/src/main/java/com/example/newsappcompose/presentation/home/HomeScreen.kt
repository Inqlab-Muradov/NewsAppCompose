package com.example.newsappcompose.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.presentation.home.components.NewsColumn
import com.example.newsappcompose.presentation.home.components.NewsRow
import com.example.newsappcompose.presentation.home.components.SearchBar
import com.example.newsappcompose.presentation.home.components.SetBottomBar
import com.example.newsappcompose.presentation.home.components.TopBar
import com.example.newsappcompose.presentation.navigation.ScreenRoute
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier,
    onNavigationTo: (ScreenRoute)->Unit,
    selectedIndex:Int,
    onSelectedIndexChanged:(Int)->Unit,
    homeState : HomeUiState,
    onNewsClick : (rowItem:NewsModel)->Unit,
    onSearchQuery:(String)->Unit,
) {
    Scaffold(
        modifier = modifier.background(Color.White),
        topBar = {TopBar()},
        bottomBar= { SetBottomBar(onNavigationTo,selectedIndex,onSelectedIndexChanged) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(start = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start){
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = getCurrentDate(), style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight(500),
                            color = Color.Gray
                        )
                    )
                }

                NewsRow(homeState, onNewsClick)
                SearchBar(onSearchQuery,homeState)
                if (homeState.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(vertical = 16.dp),
                        strokeWidth = 4.dp,
                        trackColor = Color.Red,
                        color = Color.Black
                    )
                }
                NewsColumn(homeState,onNewsClick)
                homeState.errorMessage?.let { error ->
                    Toast.makeText(LocalContext.current, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    )
}


fun getCurrentDate():String{
    val date = Date()
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}




package com.example.newsappcompose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(modifier:Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 33.dp, start = 24.dp)
            .statusBarsPadding().background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .clip(CircleShape)
                .background(Color.Black)
        ) {
        }
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "News Catcher", style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SetPreview(){
    TopBar(Modifier)
}
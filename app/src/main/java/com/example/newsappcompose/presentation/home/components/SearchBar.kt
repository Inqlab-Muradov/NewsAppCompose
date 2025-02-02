package com.example.newsappcompose.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappcompose.R

@Composable
fun SearchBar() {
    var text by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    BasicTextField(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp, end = 24.dp)
            .onFocusChanged { focusState ->
                isActive = focusState.isFocused
            }
            .border(
                1.dp,
                color = colorResource(id = R.color.searchPlaceHolder),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.Black
        ),
        value = text,
        onValueChange = { newValue ->
            text = newValue
        },
        maxLines = 1,
        decorationBox = { innerTextField ->
            if (text.isEmpty() && !isActive) {
                Text(
                    text = "Search...", style = TextStyle(
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.searchPlaceHolder)
                    )
                )
            } else {
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SearchBar()
}

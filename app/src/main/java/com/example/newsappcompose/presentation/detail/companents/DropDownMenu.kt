package com.example.newsappcompose.presentation.detail.companents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappcompose.R
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.presentation.detail.DetailUiState

@Composable
fun DropDownMenu(
    newsItem: NewsModel,
    onAddSaveClick: (NewsModel) -> Unit,
    detailUiState: DetailUiState,
    deleteSavedClick: (NewsModel) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(32.dp))) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(colorResource(R.color.sectionBoxBg).copy(alpha = 0.5f))
        ) {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    painter = painterResource(R.drawable.dropdownmenu), null
                )
            }
            DropdownMenu(modifier = Modifier
                .background(Color.White)
                .clip(RoundedCornerShape(16.dp)),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                menuItemData.forEachIndexed { index, menuItem ->
                    DropDownMenuItem(Modifier.clickable {
                        when (index) {
                            0 -> {
                                if (!detailUiState.isSaved) onAddSaveClick(newsItem) else deleteSavedClick(
                                    newsItem
                                )
                            }

                            1 -> {}
                        }
                    }, menuItem, detailUiState)
                }
            }
        }
    }
}

@Composable
fun DropDownMenuItem(
    modifier: Modifier,
    menuItem: MenuItem,
    detailUiState: DetailUiState
) {
    Column {
        Row(
            modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(
                    if (detailUiState.isSaved && menuItem.index == 0) menuItem.filledIcon else menuItem.outlinedIcon
                ),
                null,
                modifier = Modifier
                    .size(26.dp)
                    .weight(1f),
                tint = Color.Unspecified
            )
            Box(modifier = Modifier.weight(4f)) {
                Text(
                    text = menuItem.text,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .align(Alignment.Center),
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Black
        )
    }
}

data class MenuItem(
    val text: String,
    val outlinedIcon: Int,
    val filledIcon: Int,
    val index: Int
)

val menuItemData: List<MenuItem> = listOf(
    MenuItem("Save", R.drawable.outlinedsave, R.drawable.filledsave, 0),
    MenuItem("Share", R.drawable.shareicon, R.drawable.fillledhome, 1)
)
package com.example.newsappcompose.presentation.home.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newsappcompose.R
import com.example.newsappcompose.presentation.navigation.ScreenRoute


@Composable
fun SetBottomBar(
    onNavigateTo: (ScreenRoute) -> Unit,
    selectedIndex: Int,
    onSelectedIndexChanged: (Int) -> Unit
) {
    Column {
        HorizontalDivider(modifier = Modifier.padding(bottom = 6.dp),
            thickness = 2.dp, color = Color.Black)
        Row(
            modifier = Modifier
                .navigationBarsPadding()
        ) {
            iconList.forEachIndexed { index, iconItem ->
                ClickableIcon(
                    isSelected = selectedIndex == index,
                    selectedIcon = iconItem.selectedImage,
                    unselectedIcon = iconItem.unSelectedImage,
                    modifier = Modifier
                        .weight(0.5f)
                        .clickable {
                            onSelectedIndexChanged(index)
                            when (index) {
                                0 -> {
                                    onNavigateTo(ScreenRoute.ScreenHome)
                                }

                                1 -> {
                                    onNavigateTo(ScreenRoute.ScreenFav)
                                }
                            }
                        }
                )
            }
        }
    }

}

@Composable
fun ClickableIcon(
    modifier: Modifier,
    isSelected: Boolean,
    selectedIcon: Int,
    unselectedIcon: Int
) {
    Icon(
        modifier = modifier.size(32.dp),
        painter = if (isSelected) painterResource(id = selectedIcon) else painterResource(id = unselectedIcon),
        contentDescription = null
    )
}

data class IconItem(
    val selectedImage: Int,
    val unSelectedImage: Int
)

val iconList: List<IconItem> = listOf(
    IconItem(R.drawable.fillledhome, R.drawable.outlinedhome),
    IconItem(R.drawable.filledsave, R.drawable.outlinedsave)
)
//
//@Preview(showBackground = true)
//@Composable
//private fun SetPreview(){
//    SetBottomBar()
//}
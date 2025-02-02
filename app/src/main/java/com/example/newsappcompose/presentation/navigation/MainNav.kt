package com.example.newsappcompose.presentation.navigation
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsappcompose.presentation.favourites.SetFavScreen
import com.example.newsappcompose.presentation.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun SetMainNav() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    val onNavigateTo: (ScreenRoute) -> Unit = { screenRoute ->
        when (screenRoute) {
            is ScreenRoute.ScreenHome -> {
                navController.navigate(ScreenRoute.ScreenHome)
            }

            is ScreenRoute.ScreenFav -> {
                navController.navigate(ScreenRoute.ScreenFav)
            }
        }
    }
    NavHost(navController = navController, startDestination = ScreenRoute.ScreenHome) {
        composable<ScreenRoute.ScreenHome> {
            HomeScreen(modifier = Modifier, onNavigateTo, selectedIndex,
                onSelectedIndexChanged = { newIndex ->
                    selectedIndex = newIndex
                })
        }
        composable<ScreenRoute.ScreenFav> {
            SetFavScreen(modifier = Modifier, onNavigateTo, selectedIndex,
                onSelectedIndexChanged = { newIndex ->
                    selectedIndex = newIndex
                })
        }
    }
}

sealed class ScreenRoute {
    @Serializable
    data object ScreenHome : ScreenRoute()

    @Serializable
    data object ScreenFav : ScreenRoute()
}

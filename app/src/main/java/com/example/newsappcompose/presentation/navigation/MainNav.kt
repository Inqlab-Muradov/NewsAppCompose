package com.example.newsappcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.presentation.detail.DetailIntent
import com.example.newsappcompose.presentation.detail.DetailScreen
import com.example.newsappcompose.presentation.detail.DetailViewModel
import com.example.newsappcompose.presentation.favourites.FavouritesUiState
import com.example.newsappcompose.presentation.favourites.FavouritesViewModel
import com.example.newsappcompose.presentation.favourites.SetFavScreen
import com.example.newsappcompose.presentation.home.HomeIntent
import com.example.newsappcompose.presentation.home.HomeScreen
import com.example.newsappcompose.presentation.home.HomeViewModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun SetMainNav() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    val onNavigateTo: (ScreenRoute) -> Unit = { screenRoute ->

        when (screenRoute) {
            is ScreenRoute.ScreenHome -> {
                navController.navigate(ScreenRoute.ScreenHome) {
                    popUpTo(ScreenRoute.ScreenFav) {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            is ScreenRoute.ScreenFav -> {
                navController.navigate(ScreenRoute.ScreenFav)
                {
                    popUpTo(ScreenRoute.ScreenHome) {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            else -> {}
        }
    }
    NavHost(navController = navController, startDestination = ScreenRoute.ScreenHome) {
        composable<ScreenRoute.ScreenHome> {
            val viewModel = hiltViewModel<HomeViewModel>()
            val vmState by viewModel.homeUiState.collectAsStateWithLifecycle()

            HomeScreen(
                modifier = Modifier,
                onNavigateTo,
                selectedIndex,
                onSelectedIndexChanged = { newIndex ->
                    selectedIndex = newIndex
                },
                homeState = vmState,
                onNewsClick = { rowItem ->
                    navController.navigate(ScreenRoute.ScreenDetail(rowItem)) {
                        launchSingleTop = true
                    }
                },
                onSearchQuery = { newValue ->
                    viewModel.processIntent(HomeIntent.SearchedQueryChanged(newValue))
                }
            )
        }
        composable<ScreenRoute.ScreenFav> {
            val favouritesViewModel = hiltViewModel<FavouritesViewModel>()
            favouritesViewModel.getSavedNews()
            val vmState by favouritesViewModel.favouritesUiState.collectAsStateWithLifecycle()
            SetFavScreen(modifier = Modifier, onNavigateTo, selectedIndex,
                onSelectedIndexChanged = { newIndex ->
                    selectedIndex = newIndex
                }, vmState, onNewsClick = { newsItem ->
                    navController.navigate(ScreenRoute.ScreenDetail(newsItem)){
                        popUpTo(ScreenRoute.ScreenFav){
                            inclusive  = false
                        }
                    }
                })
        }

        composable<ScreenRoute.ScreenDetail>(
            typeMap = mapOf(
                typeOf<NewsModel>() to CustomNavType.newsType
            )
        ) {
            val detailViewModel = hiltViewModel<DetailViewModel>()
            val arguments = it.toRoute<ScreenRoute.ScreenDetail>()
            detailViewModel.checkIsSave(arguments.newsItem.urlToImage)
            val detailVmState by detailViewModel.detailUiState.collectAsStateWithLifecycle()
            DetailScreen(newsItem = arguments.newsItem, onBackClick = {
                if(navController.previousBackStackEntry !=null){
                    navController.popBackStack()
                }
            }, onAddSaveClick = { newsItem ->
                detailViewModel.processIntent(DetailIntent.OnAddClick(newsItem))
            }, detailUiState = detailVmState, deleteSavedClick = { newsItem ->
                detailViewModel.processIntent(DetailIntent.DeleteSavedItem(newsItem))
            })
        }
    }
}

sealed class ScreenRoute {
    @Serializable
    data object ScreenHome : ScreenRoute()

    @Serializable
    data object ScreenFav : ScreenRoute()

    @Serializable
    data class ScreenDetail(
        val newsItem: NewsModel
    ) : ScreenRoute()
}

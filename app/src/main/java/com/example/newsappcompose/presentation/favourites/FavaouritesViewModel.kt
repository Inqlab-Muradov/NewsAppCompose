package com.example.newsappcompose.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.domain.usecase.GetSavedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel@Inject constructor(
    private val getSavedNewsUseCase: GetSavedNewsUseCase
):ViewModel() {

    private val _favouritesUiState = MutableStateFlow(FavouritesUiState())
    val favouritesUiState : StateFlow<FavouritesUiState> = _favouritesUiState

    fun getSavedNews(){
        viewModelScope.launch {
            getSavedNewsUseCase.getSavedNews(
                onSuccess = {savedList->
                    _favouritesUiState.value = _favouritesUiState.value.copy(savedList =savedList )
                },
                onError = {error->
                    _favouritesUiState.value = _favouritesUiState.value.copy(error =error.toString() )
                },
                onLoading = {
                    _favouritesUiState.value  =_favouritesUiState.value.copy(loading = true)
                },
                onFinished = {
                    _favouritesUiState.value = _favouritesUiState.value.copy(loading = false)
                }
            )
        }
    }

}
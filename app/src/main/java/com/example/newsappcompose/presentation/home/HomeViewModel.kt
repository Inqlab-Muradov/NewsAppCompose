package com.example.newsappcompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.data.mapper.toNewsModel
import com.example.newsappcompose.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    val getNewsUseCase: GetNewsUseCase
):ViewModel() {

    init {
        getWsjNews()
    }

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState:StateFlow<HomeUiState> = _homeUiState

    private fun getWsjNews(){
        viewModelScope.launch {
            getNewsUseCase.execute(
                onSuccess = {result->
                    _homeUiState.value = _homeUiState.value.copy(result?.articles?.toNewsModel())
                },
                onError = {errorMessage->
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = errorMessage.toString())
                }
            )
        }
    }
}


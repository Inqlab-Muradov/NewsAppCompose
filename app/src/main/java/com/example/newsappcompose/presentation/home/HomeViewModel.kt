package com.example.newsappcompose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.data.mapper.toNewsModel
import com.example.newsappcompose.domain.usecase.GetUsNewsUseCase
import com.example.newsappcompose.domain.usecase.GetVsjNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getVsjNewsUseCase: GetVsjNewsUseCase,
    private val getUsNewsUseCase: GetUsNewsUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        getWsjNews()
        getUsNews()
    }


    fun processIntent(intent:HomeIntent){
        when(intent){
            is HomeIntent.SearchedQueryChanged->{
                filterNews(query = intent.query)
            }
        }
    }

    private fun filterNews(query:String){
        _homeUiState.update { it.copy(searchQuery = query) }

        if (query.isEmpty()){
            getUsNews()
            return
        }
        _homeUiState.update {state->
            val filteredList = _homeUiState.value.usNewsList?.filter {
                it.title.contains(query, ignoreCase = true)
            }
            state.copy(filteredUsNews = filteredList)
        }
    }

    private fun getWsjNews() {
        viewModelScope.launch {
            getVsjNewsUseCase.getVsjNews(
                onSuccess = { result ->
                    _homeUiState.value = _homeUiState.value.copy(vsjNewsList = result?.articles?.toNewsModel())
                },
                onError = { errorMessage ->
                    _homeUiState.value =
                        _homeUiState.value.copy(errorMessage = errorMessage.toString())
                },
                onLoading = {
                },
                onFinished = {
                    _homeUiState.value = _homeUiState.value.copy(loading = false)
                }
            )
        }
    }

    private fun getUsNews() {
        viewModelScope.launch {
            getUsNewsUseCase.getUsNews(
                onSuccess = { result ->
                    _homeUiState.value = _homeUiState.value.copy(usNewsList = result?.articles?.toNewsModel())
                },
                onError = { error ->
                    _homeUiState.value = _homeUiState.value.copy(errorMessage = error.toString())
                },
                onLoading = {
                    _homeUiState.update {
                        it.copy(loading = true)
                    }
                },
                onFinished = {
                    _homeUiState.value = _homeUiState.value.copy(loading = false)
                }
            )
        }
    }
}


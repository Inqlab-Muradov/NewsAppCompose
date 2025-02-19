package com.example.newsappcompose.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsappcompose.domain.model.NewsModel
import com.example.newsappcompose.domain.usecase.AddNewsUseCase
import com.example.newsappcompose.domain.usecase.CheckIsSavedUseCase
import com.example.newsappcompose.domain.usecase.DeleteSavedItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel@Inject constructor(
    private val addNewsUseCase: AddNewsUseCase,
    private val checkIsSavedUseCase: CheckIsSavedUseCase,
    private val deleteSavedItemUseCase: DeleteSavedItemUseCase
) : ViewModel() {

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState : StateFlow<DetailUiState> =_detailUiState

    fun processIntent(intent:DetailIntent){
        when(intent){
            is DetailIntent.OnAddClick->{
                addNews(intent.newsModel)
            }
            is DetailIntent.DeleteSavedItem->{
                deleteSavedItem(intent.newsModel)
            }
        }
    }

    private fun addNews(newsItem:NewsModel){
        viewModelScope.launch {
            addNewsUseCase.addNews(newsItem)
            _detailUiState.value = _detailUiState.value.copy(isSaved =true)
        }
    }
    private fun deleteSavedItem(newsModel: NewsModel){
        viewModelScope.launch {
            deleteSavedItemUseCase.deleteSavedItem(newsModel)
            _detailUiState.value = _detailUiState.value.copy(isSaved =false)
        }
    }

    fun checkIsSave(urlToImage:String){
        viewModelScope.launch {
            checkIsSavedUseCase.checkIsSaved(
                urlToImage = urlToImage,
                onSuccess = {
                    _detailUiState.value = _detailUiState.value.copy(isSaved = true)
                },
                onFailure = {
                    _detailUiState.value = _detailUiState.value.copy(isSaved = false)
                }
            )
        }
    }
}
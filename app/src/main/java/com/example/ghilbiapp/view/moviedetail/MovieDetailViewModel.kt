package com.example.ghilbiapp.view.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghilbiapp.domain.usecases.MovieDetailUseCase
import com.example.ghilbiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        viewModelScope.launch {
            _uiState.update { MovieDetailUiState.Loading }
            movieDetailUseCase.invoke("2baf70d1-42bb-4437-b551-e5fed5a87abe").collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiState.update { MovieDetailUiState.Success(result.data!!) }
                    }
                    is Resource.Error -> {
                        _uiState.update { MovieDetailUiState.Error(result.message ?: "Unknown Error") }
                    }
                }
            }
        }
    }
}
package com.example.ghilbiapp.view.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.ghilbiapp.domain.usecases.MovieDetailUseCase
import com.example.ghilbiapp.utils.Resource
import com.example.ghilbiapp.view.navigation.MovieDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieDetailUseCase: MovieDetailUseCase
): ViewModel() {
    private val routeData = savedStateHandle.toRoute<MovieDetailRoute>()
    private val movieId = routeData.movieId

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetails(movieId)
    }

    private fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            _uiState.update { MovieDetailUiState.Loading }
            movieDetailUseCase.invoke(movieId).collect { result ->
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
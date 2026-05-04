package com.example.ghilbiapp.view.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import com.example.ghilbiapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: GhibliMovieRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LibraryUiState>(LibraryUiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _uiState.update { LibraryUiState.Loading }
            when(val movies = repository.fetchMovies()) {
                is Resource.Success -> {
                    _uiState.update { LibraryUiState.Success(movies.data!!) }
                }
                is Resource.Error -> {
                    _uiState.update { LibraryUiState.Error(movies.message ?: "Unknown Error") }
                }
            }
        }
    }
}
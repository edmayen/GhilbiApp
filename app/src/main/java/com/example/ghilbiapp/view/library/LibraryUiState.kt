package com.example.ghilbiapp.view.library

import com.example.ghilbiapp.domain.model.GhibliMovieModel

sealed interface LibraryUiState{
    object Idle: LibraryUiState
    object Loading: LibraryUiState
    data class Success(val movies: List<GhibliMovieModel>): LibraryUiState
    data class Error(val message: String): LibraryUiState
}

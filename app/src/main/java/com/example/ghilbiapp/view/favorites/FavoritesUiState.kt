package com.example.ghilbiapp.view.favorites

import com.example.ghilbiapp.domain.model.FavoriteMovieModel

sealed interface FavoritesUiState {
    object Idle : FavoritesUiState
    object Loading : FavoritesUiState
    data class Success(val movies: List<FavoriteMovieModel>) : FavoritesUiState
    data class Error(val message: String) : FavoritesUiState
}

package com.example.ghilbiapp.view.moviedetail

import com.example.ghilbiapp.domain.model.MovieDetailModel


sealed interface MovieDetailUiState {
    object Idle: MovieDetailUiState
    data class Success(val movie: MovieDetailModel): MovieDetailUiState
    data class Error(val message: String):  MovieDetailUiState
    object Loading : MovieDetailUiState
}
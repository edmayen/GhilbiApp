package com.example.ghilbiapp.domain.usecases

import com.example.ghilbiapp.domain.model.MovieDetailModel
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: GhibliMovieRepository
) {
    suspend operator fun invoke(movie: MovieDetailModel, isCurrentlyFavorite: Boolean) {
        repository.toggleFavorite(movie, isCurrentlyFavorite)
    }
}
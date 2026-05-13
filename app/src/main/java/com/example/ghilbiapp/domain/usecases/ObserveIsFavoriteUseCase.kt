package com.example.ghilbiapp.domain.usecases

import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsFavoriteUseCase @Inject constructor(
    private val repository: GhibliMovieRepository
) {
    operator fun invoke(movieId: String): Flow<Boolean> {
        return repository.observeIsFavorite(movieId)
    }
}
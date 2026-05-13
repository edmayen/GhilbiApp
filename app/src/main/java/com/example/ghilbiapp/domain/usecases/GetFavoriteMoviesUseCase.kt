package com.example.ghilbiapp.domain.usecases

import com.example.ghilbiapp.data.db.FavoriteMovieEntity
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: GhibliMovieRepository
) {
    operator fun invoke(): Flow<List<FavoriteMovieEntity>> {
        return repository.observeFavoriteMovies()
    }
}
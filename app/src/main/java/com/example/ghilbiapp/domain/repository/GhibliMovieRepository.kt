package com.example.ghilbiapp.domain.repository

import com.example.ghilbiapp.data.db.FavoriteMovieEntity
import com.example.ghilbiapp.domain.model.CharacterModel
import com.example.ghilbiapp.domain.model.GhibliMovieModel
import com.example.ghilbiapp.domain.model.MovieDetailModel
import com.example.ghilbiapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GhibliMovieRepository {

    suspend fun fetchMovies(): Resource<List<GhibliMovieModel>>

    suspend fun getMovieDetail(movieId: String): Resource<MovieDetailModel>

    suspend fun fetchMovieCharacters(charactersUrls: List<String>): Resource<List<CharacterModel>>

    fun observeFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    fun observeIsFavorite(id: String): Flow<Boolean>

    suspend fun toggleFavorite(movie: MovieDetailModel, isCurrentlyFavorite: Boolean)
}
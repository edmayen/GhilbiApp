package com.example.ghilbiapp.data.repository

import com.example.ghilbiapp.data.api.ApiService
import com.example.ghilbiapp.data.api.response.toDomain
import com.example.ghilbiapp.data.db.FavoriteMovieDao
import com.example.ghilbiapp.data.db.FavoriteMovieEntity
import com.example.ghilbiapp.domain.model.CharacterModel
import com.example.ghilbiapp.domain.model.GhibliMovieModel
import com.example.ghilbiapp.domain.model.MovieDetailModel
import com.example.ghilbiapp.domain.model.toEntity
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import com.example.ghilbiapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GhibliMovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val favoriteDao: FavoriteMovieDao
) : GhibliMovieRepository {

    override suspend fun fetchMovies(): Resource<List<GhibliMovieModel>> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.fetchMovies()
                Resource.Success(response.map { it.toDomain() })
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Unknown error")
            }
        }

    override suspend fun getMovieDetail(movieId: String): Resource<MovieDetailModel> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMovieDetail(movieId)
                Resource.Success(response.toDomain())
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Unknown error")
            }
        }

    override suspend fun fetchMovieCharacters(charactersUrls: List<String>): Resource<List<CharacterModel>> =
        withContext(Dispatchers.IO) {
            try {
                val validIds = charactersUrls.mapNotNull { characterUrls ->
                    val id = characterUrls.substringAfterLast("/")
                    id.ifBlank { null }
                }

                if (validIds.isEmpty()) {
                    return@withContext Resource.Success(emptyList())
                }

                val charactersList = coroutineScope {
                    val deferredCharacter = validIds.map { id ->
                        async {
                            apiService.getCharacterById(id).toDomain()
                        }
                    }
                    deferredCharacter.awaitAll()
                }
                Resource.Success(charactersList)
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Error fetching characters")
            }
        }

    override fun observeFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return favoriteDao.getAllFavorites()
    }

    override fun observeIsFavorite(id: String): Flow<Boolean> {
        return favoriteDao.isFavorite(id)
    }

    override suspend fun toggleFavorite(
        movie: MovieDetailModel,
        isCurrentlyFavorite: Boolean
    ) {
        withContext(Dispatchers.IO) {
            if (isCurrentlyFavorite) {
                favoriteDao.deleteFavorite(movie.id)
            } else {
                favoriteDao.insertFavorite(movie.toEntity())
            }
        }
    }
}
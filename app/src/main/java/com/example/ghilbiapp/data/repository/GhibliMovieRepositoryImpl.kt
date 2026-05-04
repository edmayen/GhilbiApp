package com.example.ghilbiapp.data.repository

import com.example.ghilbiapp.data.api.ApiService
import com.example.ghilbiapp.data.api.response.toDomain
import com.example.ghilbiapp.domain.model.GhibliMovieModel
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import com.example.ghilbiapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GhibliMovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
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
}
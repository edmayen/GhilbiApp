package com.example.ghilbiapp.domain.usecases

import com.example.ghilbiapp.domain.model.MovieDetailModel
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import com.example.ghilbiapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: GhibliMovieRepository
) {
    suspend operator fun invoke(movieId: String): Flow<Resource<MovieDetailModel>> =
        flow {
            val response = repository.getMovieDetail(movieId)
            emit(response)
        }
}
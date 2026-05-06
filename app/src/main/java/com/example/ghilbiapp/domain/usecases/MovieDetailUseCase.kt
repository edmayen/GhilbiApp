package com.example.ghilbiapp.domain.usecases

import com.example.ghilbiapp.domain.model.MovieDetailModel
import com.example.ghilbiapp.domain.repository.GhibliMovieRepository
import com.example.ghilbiapp.utils.Resource
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val repository: GhibliMovieRepository
) {
    suspend operator fun invoke(movieId: String): Resource<MovieDetailModel> =
        repository.getMovieDetail(movieId)
}
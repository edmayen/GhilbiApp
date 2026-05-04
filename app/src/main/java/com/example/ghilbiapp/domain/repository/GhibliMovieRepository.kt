package com.example.ghilbiapp.domain.repository

import com.example.ghilbiapp.domain.model.GhibliMovieModel
import com.example.ghilbiapp.utils.Resource

interface GhibliMovieRepository {

    suspend fun fetchMovies(): Resource<List<GhibliMovieModel>>
}
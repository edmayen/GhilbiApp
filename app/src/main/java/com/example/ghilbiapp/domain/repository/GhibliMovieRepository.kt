package com.example.ghilbiapp.domain.repository

import com.example.ghilbiapp.domain.model.CharacterModel
import com.example.ghilbiapp.domain.model.GhibliMovieModel
import com.example.ghilbiapp.domain.model.MovieDetailModel
import com.example.ghilbiapp.utils.Resource

interface GhibliMovieRepository {

    suspend fun fetchMovies(): Resource<List<GhibliMovieModel>>

    suspend fun getMovieDetail(movieId: String): Resource<MovieDetailModel>

    suspend fun fetchMovieCharacters(charactersUrls: List<String>): Resource<List<CharacterModel>>
}